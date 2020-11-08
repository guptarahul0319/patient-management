package com.karkinos.patientmanagement.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import com.karkinos.patientmanagement.dao.IPatientRepository;
import com.karkinos.patientmanagement.dao.dto.Patient;
import com.karkinos.patientmanagement.enums.GenderEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The type Patient service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

  @Mock
  private IPatientRepository patientRepository;

  /**
   * The Patient without id.
   */
  Patient patientWithoutId;
  /**
   * The Patient with id.
   */
  Patient patientWithId;

  /**
   * The Patient without full name.
   */
  Patient patientWithoutFullName;

  /**
   * The Patient service.
   */
  @InjectMocks
  private PatientService patientService;

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

    patientWithoutFullName = Patient.builder()
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
   * Test create patient should pass when passed valid arguments.
   */
  @Test
  public void testCreatePatientShouldPassWhenPassedValidArguments() {
    Mockito.when(patientRepository.saveAndFlush(patientWithoutId)).thenReturn(patientWithId);
    Patient patient = patientService.createPatient(patientWithoutId);
    assertEquals(patientWithId, patient);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreatePatientShouldThrowExceptionWhenPassedArgumentsNull() {
    patientService.createPatient(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreatePatientShouldThrowExceptionWhenPassedPatientWithoutFullName() {
    patientService.createPatient(patientWithoutFullName);
  }

  @Test
  public void testCreatePatientShouldUpdatePatientWhenPassedPatientWithId() {
    Mockito.when(patientRepository.saveAndFlush(patientWithId)).thenReturn(patientWithId);
    final Patient response = patientService.createPatient(patientWithId);
    assertEquals(patientWithId, response);
  }

  @Test
  public void testGetPatientsShouldGetPatientMatchingFullName() {
    Mockito.when(patientRepository.findByFullNameLike("%Rahul%")).thenReturn(Arrays.asList(patientWithId));
    final List<Patient> response = patientService.getPatients("Rahul");
    assertEquals(Arrays.asList(patientWithId), response);
  }

  @Test(expected = DataAccessException.class)
  public void testGetPatientsShouldThrowExceptionWhenDatabaseNotConnected() {
    Mockito.when(patientRepository.findByFullNameLike("%Rahul%"))
        .thenThrow(new DataAccessException("Database disconnected.") {
          @Override
          public String getMessage() {
            return super.getMessage();
          }
        });
    patientService.getPatients("Rahul");
  }

  @Test
  public void testGetAllPatientsShouldGetPatientMatchingFullName() {
    Mockito.when(patientRepository.findAll()).thenReturn(Arrays.asList(patientWithId));
    final List<Patient> response = patientService.getAllPatient();
    assertEquals(Arrays.asList(patientWithId), response);
  }

  @Test(expected = DataAccessException.class)
  public void testGetAllPatientsShouldThrowExceptionWhenDatabaseNotConnected() {
    Mockito.when(patientRepository.findAll())
        .thenThrow(new DataAccessException("Database disconnected.") {
          @Override
          public String getMessage() {
            return super.getMessage();
          }
        });
    patientService.getAllPatient();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdatePatientShouldThrowExceptionWhenPassedInValidArguments() {
    patientService.createPatient(patientWithoutFullName);
  }

  @Test
  public void testUpdatePatientShouldUpdatePatientWhenPassedPatientWithId() {
    Mockito.when(patientRepository.saveAndFlush(patientWithId)).thenReturn(patientWithId);
    final Patient response = patientService.updatePatient(patientWithId);
    assertEquals(patientWithId, response);
  }

  @Test
  public void testDeletePatientShouldReturnTrueWhenPassedValidId() {
    Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patientWithId));
    doNothing().when(patientRepository).delete(patientWithId);
    final Boolean response = patientService.deletePatient(1L);
    assertEquals(Boolean.TRUE, response);
  }

  @Test
  public void testDeletePatientShouldReturnFalseWhenPassedInValidId() {
    final Boolean response = patientService.deletePatient(null);
    assertEquals(Boolean.FALSE, response);
  }

  @Test
  public void testDeletePatientShouldReturnFalseWhenUnableToFindPatientWithId() {
    Mockito.when(patientRepository.findById(1L)).thenReturn(null);
    final Boolean response = patientService.deletePatient(1L);
    assertEquals(Boolean.FALSE, response);
  }
}
