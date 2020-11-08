package com.karkinos.patientmanagement.dao;

import com.karkinos.patientmanagement.dao.dto.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface Patient repository.
 */
@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

  /**
   * Find by name patient.
   *
   * @param fullName the full name
   * @return the patient
   */
  @Query("from Patient p where lower(p.fullName) like lower(?1)")
  List<Patient> findByFullNameLike(final String fullName);
}
