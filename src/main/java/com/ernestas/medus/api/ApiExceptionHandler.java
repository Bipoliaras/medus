package com.ernestas.medus.api;

import com.ernestas.medus.entities.error.ExceptionResponse;
import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiExceptionHandler
 *
 * Implements error handling for the external Mesh API
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  /*
   * Use the custom response body for the main controller errors
   */
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
      Object body, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    ExceptionResponse exceptionResponse = createExceptionResponse(ex);
    return new ResponseEntity<>(exceptionResponse,headers,status);
  }

//  @ExceptionHandler(value={BonusExpiredException.class,BonusAwardAlreadyExistsException.class})
//  @ResponseStatus(value=HttpStatus.PRECONDITION_FAILED)
//  public @ResponseBody ExceptionResponse handlePreConditionFailedException(Exception e) {
//    return createExceptionResponse(e);
//  }
//
//  /*
//   * Handle invalid request exceptions
//   */
//  @ExceptionHandler(value={BadRequestException.class,ConstraintViolationException.class})
//  @ResponseStatus(value=HttpStatus.BAD_REQUEST)
//  public @ResponseBody ExceptionResponse handleBadRequestException(Exception e) {
//    return createExceptionResponse(e);
//  }
//
//  /*
//   * Handle not authorized exceptions
//   */
//  @ExceptionHandler(value={AuthorizationException.class})
//  @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
//  public @ResponseBody ExceptionResponse handleUnauthorizedException(Exception e) {
//    return createExceptionResponse(e);
//  }
//
//  /*
//   * Handle conflict exceptions
//   */
//  @ExceptionHandler(value={InvalidStateException.class})
//  @ResponseStatus(value=HttpStatus.CONFLICT)
//  public @ResponseBody ExceptionResponse handleConflictException(Exception e) {
//    return createExceptionResponse(e);
//  }
//
//  /*
//   * Handle resource not found exceptions
//   */
//  @ExceptionHandler(value={
//      GameNotFoundException.class,
//      PlayerNotFoundException.class,
//      PlayNotFoundException.class,
//      InvalidRouteException.class,
//      IgpCodeNotFoundException.class,
//      NotFoundException.class})
//  @ResponseStatus(value=HttpStatus.NOT_FOUND)
//  public @ResponseBody ExceptionResponse handleResourceNotFoundException(Exception e) {
//    return createExceptionResponse(e);
//  }

  /*
   * Catch-all error handler
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody
  ExceptionResponse handleException(Exception e) throws Exception {
    return createExceptionResponse(e);
  }

  /*
   * Creates a mesh exception response from a runtime exception
   */
  private ExceptionResponse createExceptionResponse(Exception ex) {
    String code = ex.getClass().getSimpleName();
    String msg = "error.message." + code;
    String debug = null;
    if (ex instanceof ConstraintViolationException) {
      ConstraintViolationException cve = (ConstraintViolationException) ex;
      debug = cve.getConstraintViolations().toString();
    } else {
      debug = ex.getMessage();
    }
    return new ExceptionResponse(code, msg, debug, "NONE", Thread.currentThread().getName(), new Date());
  }

}
