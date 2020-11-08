package com.karkinos.patientmanagement.service.impl;

import com.karkinos.patientmanagement.dao.IPatientRepository;
import com.karkinos.patientmanagement.dao.dto.Patient;
import com.karkinos.patientmanagement.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The type Patient service.
 */
@Service
public class PatientService implements IPatientService {

  /**
   * The Logger.
   */
  private Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

  /**
   * The User repository.
   */
  @Autowired
  private IPatientRepository patientRepository;

  /**
   * Create patient.
   *
   * @param patient the patient
   * @return the patient
   */
  @Override
  @Transactional
  public Patient createPatient(Patient patient) {

    if (Optional.ofNullable(patient).isPresent()) {
      if (Optional.ofNullable(patient.getId()).isPresent()) {
        LOGGER.info("The patient id is present invoking update patient call.");
        return updatePatient(patient);
      } else {
        if (Optional.ofNullable(patient.getGender()).isPresent()
            && Optional.ofNullable(patient.getFullName()).isPresent()
            && Optional.ofNullable(patient.getPhoneNumber()).isPresent()) {
          final Patient patientResponse = patientRepository.saveAndFlush(patient);
          LOGGER.debug("The patient created successfully: {}", patientResponse);
          return patientResponse;
        } else {
          LOGGER.error("The patient details provided are: {}", patient);
          throw new IllegalArgumentException("Invalid Patient Details Passed.");
        }
      }
    } else {
      LOGGER.error("The patient details provided are: {}", patient);
      throw new IllegalArgumentException("Invalid Patient Details Passed.");
    }
  }

  /**
   * Update patient patient.
   *
   * @param patient the patient
   * @return the patient
   */
  @Override
  @Transactional
  public Patient updatePatient(final Patient patient) {

    if (Optional.ofNullable(patient).isPresent()
        && Optional.ofNullable(patient.getId()).isPresent()
        && Optional.ofNullable(patient.getPhoneNumber()).isPresent()
        && Optional.ofNullable(patient.getGender()).isPresent()
        && Optional.ofNullable(patient.getFullName()).isPresent()) {
      final Patient patientResponse = patientRepository.saveAndFlush(patient);
      LOGGER.info("The patient update successfully : {}", patientResponse);
      return patientResponse;
    } else {
      throw new IllegalArgumentException("Invalid user to update.");
    }
  }

  /**
   * Gets patient.
   *
   * @param fullName the full name
   * @return the patient
   */
  @Override
  public List<Patient> getPatients(String fullName) {
    return patientRepository.findByFullNameLike("%" + fullName + "%");
  }

  /**
   * Gets all patient.
   *
   * @return the all patient
   */
  @Override
  @Transactional(readOnly = true)
  public List<Patient> getAllPatient() {
    return patientRepository.findAll();
  }

  /**
   * Delete patient boolean.
   *
   * @param patientId the patient id
   * @return the boolean
   */
  @Override
  @Transactional
  public Boolean deletePatient(final Long patientId) {

    if (Optional.ofNullable(patientId).isPresent()) {
      final Optional<Patient> patient = patientRepository.findById(patientId);
      if (Optional.ofNullable(patient).isPresent()) {
        patientRepository.delete(patient.get());
        return Boolean.TRUE;
      } else {
        return Boolean.FALSE;
      }
    } else {
      return Boolean.FALSE;
    }
  }
}
