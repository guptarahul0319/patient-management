package com.karkinos.patientmanagement.dao.dto;

import com.karkinos.patientmanagement.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * The type Patient.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient {

  /**
   * The constant serialVersionUID.
   */
  private static final Long serialVersionUID = 1L;

  /**
   * The Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * The Full name.
   */
  @Column(name = "fullName")
  @NotNull
  private String fullName;

  /**
   * The Gender.
   */
  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  @NotNull
  private GenderEnum gender;

  /**
   * The Phone number.
   */
  @Column(name = "phone_number")
  @NotNull
  private Long phoneNumber;
}
