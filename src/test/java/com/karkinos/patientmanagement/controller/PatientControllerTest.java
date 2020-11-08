package com.karkinos.patientmanagement.controller;

import static org.junit.Assert.assertEquals;

import com.karkinos.patientmanagement.dao.dto.Patient;
import com.karkinos.patientmanagement.enums.GenderEnum;
import com.karkinos.patientmanagement.service.IPatientService;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

/**
 * The type Patient controller test.
 */
@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest {

  @Mock
  private IPatientService patientService;

  @InjectMocks
  private PatientController patientController;

  /**
   * The Patient without id.
   */
  Patient patientWithoutId;
  /**
   * The Patient with id.
   */
  Patient patientWithId;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    patientWithoutId = Patient.builder()
        .fullName("Rahul Gupta")
        .gender(GenderEnum.MALE)
        .phoneNumber(1234567890L)
        .build();
    patientWithId = Patient.builder()
        .id(1L)
        .fullName("Rahul Gupta")
        .gender(GenderEnum.MALE)
        .phoneNumber(1234567890L)
        .build();

  }

  /**
   * Test create patient.
   */
  @Test
  public void testCreatePatientShouldPassWhenPassedValidArguments() {
    Mockito.when(patientService.createPatient(patientWithoutId)).thenReturn(patientWithId);
    ResponseEntity<Patient> patient = patientController.createPatient(patientWithoutId);
    assertEquals(patientWithId, patient.getBody());
  }

  /**
   * Test create patient should throw exception when passed in valid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePatientShouldThrowExceptionWhenPassedInValidArguments() {
    Mockito.when(patientService.createPatient(null)).thenThrow(new IllegalArgumentException("Invalid input."));
    patientController.createPatient(null);
  }

  /**
   * Test get all patient should pass when passed valid arguments.
   */
  @Test
  public void testGetAllPatientShouldPassWhenPassedValidArguments() {
    Mockito.when(patientService.getAllPatient()).thenReturn(Arrays.asList(patientWithId));
    ResponseEntity<List<Patient>> patient = patientController.getAllPatient();
    assertEquals(Arrays.asList(patientWithId), patient.getBody());
  }

  /**
   * Test get all patient should throw exception when passed in valid arguments.
   */
  @Test(expected = DataAccessException.class)
  public void testGetAllPatientShouldThrowExceptionWhenPassedInValidArguments() {
    Mockito.when(patientService.getAllPatient()).thenThrow(new DataAccessException("Invalid input.") {
      @Override
      public String getMessage() {
        return super.getMessage();
      }
    });
    patientController.getAllPatient();
  }

  /**
   * Test get patients should pass when passed valid arguments.
   *
   * @throws NotFoundException the not found exception
   */
  @Test
  public void testGetPatientsShouldPassWhenPassedValidArguments() throws NotFoundException {
    Mockito.when(patientService.getPatients(patientWithId.getFullName())).thenReturn(Arrays.asList(patientWithId));
    ResponseEntity<List<Patient>> patient = patientController.getPatients(patientWithId.getFullName());
    assertEquals(Arrays.asList(patientWithId), patient.getBody());
  }

  /**
   * Test get patients should throw exception when passed in valid arguments.
   *
   * @throws NotFoundException the not found exception
   */
  @Test(expected = DataAccessException.class)
  public void testGetPatientsShouldThrowExceptionWhenPassedInValidArguments() throws NotFoundException {
    Mockito.when(patientService.getPatients(patientWithId.getFullName())).thenThrow(new DataAccessException("Invalid input.") {
      @Override
      public String getMessage() {
        return super.getMessage();
      }
    });
    patientController.getPatients(patientWithId.getFullName());
  }

  /**
   * Test update patient should pass when passed valid arguments.
   */
  @Test
  public void testUpdatePatientShouldPassWhenPassedValidArguments() {
    Mockito.when(patientService.updatePatient(patientWithId)).thenReturn(patientWithId);
    ResponseEntity<Patient> patient = patientController.updatePatient(patientWithId);
    assertEquals(patientWithId, patient.getBody());
  }

  /**
   * Test update patient should throw exception when passed in valid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdatePatientShouldThrowExceptionWhenPassedInValidArguments() {
    Mockito.when(patientService.updatePatient(null)).thenThrow(new IllegalArgumentException("Invalid input."));
    patientController.updatePatient(null);
  }

  /**
   * Test delete patient should return true when delete successful.
   */
  @Test
  public void testDeletePatientShouldReturnTrueWhenDeleteSuccessful() {
    Mockito.when(patientService.deletePatient(1L)).thenReturn(true);
    ResponseEntity<String> patient = patientController.deletePatient(1L);
    assertEquals("Deleted successfully", patient.getBody());
  }

  /**
   * Test delete patient should return true when delete unsuccessful.
   */
  @Test
  public void testDeletePatientShouldReturnTrueWhenDeleteUnsuccessful() {
    Mockito.when(patientService.deletePatient(1L)).thenReturn(false);
    ResponseEntity<String> patient = patientController.deletePatient(1L);
    assertEquals("Delete operation was unsuccessful", patient.getBody());
  }
}
