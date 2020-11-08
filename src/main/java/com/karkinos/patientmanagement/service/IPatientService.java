package com.karkinos.patientmanagement.service;

import com.karkinos.patientmanagement.dao.dto.Patient;

import java.util.List;

/**
 * The interface Patient service.
 */
public interface IPatientService {

  /**
   * Create patient patient.
   *
   * @param patient the patient
   * @return the patient
   */
  Patient createPatient(final Patient patient);

  /**
   * Update patient patient.
   *
   * @param patient the patient
   * @return the patient
   */
  Patient updatePatient(final Patient patient);

  /**
   * Gets patient.
   *
   * @param fullName the full name
   * @return the patient
   */
  List<Patient> getPatients(final String fullName);

  /**
   * Gets all patient.
   *
   * @return the all patient
   */
  List<Patient> getAllPatient();

  /**
   * Delete patient boolean.
   *
   * @param patientId the patient id
   * @return the boolean
   */
  Boolean deletePatient(final Long patientId);
}
