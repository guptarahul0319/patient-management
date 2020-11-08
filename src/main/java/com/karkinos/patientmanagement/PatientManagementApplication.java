package com.karkinos.patientmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PatientManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(PatientManagementApplication.class, args);
  }
}
