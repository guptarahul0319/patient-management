package com.karkinos.patientmanagement.exceptionhandling;

import static org.junit.Assert.assertEquals;

import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Exception handler advice test.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerAdviceTest {

  @Mock
  private HttpServletRequest httpServletRequest;

  @InjectMocks
  private ExceptionHandlerAdvice exceptionHandlerAdvice;

  /**
   * Test handle illegal argument exception.
   */
  @Test
  public void testHandleIllegalArgumentException() {

    final ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Invalid Arguments");
    final ResponseEntity<String> response = exceptionHandlerAdvice.handleIllegalArgumentException(
        httpServletRequest, new IllegalArgumentException("Invalid Input"));
    assertEquals(responseEntity.getStatusCode(), response.getStatusCode());
    assertEquals(responseEntity.getBody(), response.getBody());
  }

  /**
   * Test handle not found exception.
   */
  @Test
  public void testHandleNotFoundException() {

    final ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("Variable not found");
    final ResponseEntity<String> response = exceptionHandlerAdvice.handleNotFoundException(
        httpServletRequest, new NotFoundException("Variable not found"));
    assertEquals(responseEntity.getStatusCode(), response.getStatusCode());
    assertEquals(responseEntity.getBody(), response.getBody());
  }

  /**
   * Test handle exception.
   */
  @Test
  public void testHandleException() {

    final ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Exception occurred while executing the request.");
    final ResponseEntity<String> response = exceptionHandlerAdvice.handleException(
        httpServletRequest, new Exception("Internal Server Error"));
    assertEquals(responseEntity.getStatusCode(), response.getStatusCode());
    assertEquals(responseEntity.getBody(), response.getBody());
  }
}
