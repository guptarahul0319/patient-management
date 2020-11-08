package com.karkinos.patientmanagement.controller;

import com.karkinos.patientmanagement.dao.dto.Patient;
import com.karkinos.patientmanagement.service.IPatientService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;

/**
 * The type Patient controller.
 */
@RequestMapping("patient")
@RestController
public class PatientController {

  /**
   * The Logger.
   */
  private Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

  /**
   * The Patient service.
   */
  @Autowired
  private IPatientService patientService;

  /**
   * Create patient response entity.
   *
   * @param patient the patient
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Patient> createPatient(@RequestBody @NotNull final Patient patient) {

    LOGGER.debug("The patient to be created: {}", patient);
    final Patient patientResponse = patientService.createPatient(patient);
    return ResponseEntity.status(HttpStatus.OK)
        .body(patientResponse);
  }

  /**
   * Gets all patient.
   *
   * @return the all patient
   */
  @GetMapping
  public ResponseEntity<List<Patient>> getAllPatient() {

    LOGGER.info("The get all patient call");
    final List<Patient> patientList = patientService.getAllPatient();
    return ResponseEntity.status(HttpStatus.OK)
        .body(patientList);
  }

  /**
   * Gets patient.
   *
   * @param fullName the full name
   * @return the patient
   * @throws NotFoundException the not found exception
   */
  @GetMapping("/")
  public ResponseEntity<List<Patient>> getPatients(@RequestParam(required = true) @NotNull String fullName)
      throws NotFoundException {

    LOGGER.info("The get patient by full name : {}", fullName);
    final List<Patient> patient = patientService.getPatients(fullName);
    LOGGER.info("The patient fetched : {}", patient);
    if (Optional.ofNullable(patient).isPresent()) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(patient);
    } else {
      throw new NotFoundException("No patient found with name = " + fullName);
    }
  }

  /**
   * Update patient response entity.
   *
   * @param patient the patient
   * @return the response entity
   */
  @PutMapping
  public ResponseEntity<Patient> updatePatient(@RequestBody @NotNull final Patient patient) {

    LOGGER.info("The update patient request : {}", patient);
    final Patient patientResponse = patientService.updatePatient(patient);
    return ResponseEntity.status(HttpStatus.OK)
        .body(patientResponse);
  }

  /**
   * Delete patient response entity.
   *
   * @param patientId the patient id
   * @return the response entity
   */
  @DeleteMapping
  public ResponseEntity<String> deletePatient(@RequestParam @NotNull final Long patientId) {

    LOGGER.info("The delete patient id : {}", patientId);
    if (patientService.deletePatient(patientId)) {
      LOGGER.info("The patient is delete successfully");
      return ResponseEntity.status(HttpStatus.OK)
          .body("Deleted successfully");
    } else {
      LOGGER.info("The patient delete is unsuccessful");
      return ResponseEntity.status(HttpStatus.OK)
          .body("Delete operation was unsuccessful");
    }
  }
}
