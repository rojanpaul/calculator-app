package com.conciso.calculator.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.conciso.calculator.exception.model.CalculatorErrorResponse;

@ControllerAdvice // Handles all exceptions in the controller
public class CalculationApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Purpose : Method for handling MethodArgument Validation Exceptions
	 * Technical note : This method is called when the response body is evaluated with  @Valid on @RequestBody
	 * annotation
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("Processed Time", new Date());
		body.put("Http Request Status", httpStatus.value());
		body.put("Http Status Message", httpStatus.name());
		// Get all validation errors
//		exception.getBindingResult().getFieldErrors()
		List<String> errors = exception.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("Errors found", errors);
		return new ResponseEntity<>(body, httpHeaders, httpStatus);

	}

	

    /**
	 * Purpose : Method for handling handleHttpMessageNotReadable
	 * Technical note : This method is called when the input request contain invalid parameters
	 */
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders httpHeaders,
                                                                  HttpStatus httpStatus, 
                                                                  WebRequest request) {
    	 Map<String, Object> body = new LinkedHashMap<>();
         body.put("Processed Time", new Date());
         body.put("Http Request Status", httpStatus.value());
         //Get all validation errors
         body.put("Errors found", "All the input parameters should be integers in json array. For eg : {\"operands\": [5,2]}");
         return new ResponseEntity<>(body, httpHeaders, httpStatus);
    	
    }

	
	/**
	 * To handle ConstraintViolation exceptions. 
	 * Technical note : This method is invoked when the Class is evaluated with 
	 * @validated annotation on path variable/parameters
	 * We are also demonstrating method level annotation using @validated(groups)
	 * 
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleConstraintViolationException(HttpServletResponse response) throws IOException {
		 response.sendError(HttpStatus.BAD_REQUEST.value()); //-- can be used as a default error message
	}
	
	
	
	/**
	 * To handle specific Custom exceptions. 
	 * Technical note : User defined exceptions that are wrapped are handled here.(with ful control over response)
	 * We are also demonstrating method level annotation using @validated(groups)
	 * 
	 * @param response
	 * @throws IOException
	 */
//	  @ExceptionHandler(OperationNotFoundException.class)
//    public ResponseEntity<Object> customHandleNotFound(Exception ex, WebRequest request) {
//        CalculatorErrorResponse errors = new CalculatorErrorResponse();
//        errors.setTimestamp(LocalDateTime.now());
//        errors.setError(ex.getMessage());
//        errors.setStatus(HttpStatus.NOT_FOUND.value());
//		  return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);       
//
//    }
	
	/**
	 * To handle specific Custom exceptions. 
	 * Technical note : User defined exceptions that are wrapped are handled here.
	 * We are also demonstrating method level annotation using @validated(groups)
	 * Attributes that are to be returned can be modified in CalculatorErrorAttributes
	 * @param response
	 * @throws IOException
	 */
	@ExceptionHandler(OperationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleOperationNotFoundException(HttpServletResponse response) throws IOException {
		 response.sendError(HttpStatus.NOT_FOUND.value()); //-- can be used as a default error message
	}
	
}
