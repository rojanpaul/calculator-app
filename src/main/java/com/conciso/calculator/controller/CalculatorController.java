package com.conciso.calculator.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.conciso.calculator.model.CalculatorSvcRequest;
import com.conciso.calculator.model.CalculatorSvcResponse;
import com.conciso.calculator.service.CalculatorOperationsService;
import com.conciso.calculator.validator.model.OnMultiplyAndDivide;

/**
 * Calculator REST APIs are defined here
 * 
 * @author rojan
 *
 */
@RestController
@RequestMapping("/calculatorservice")
@Validated
public class CalculatorController {

	// Class responsible for performing actual mathematical operation
	@Autowired
	private CalculatorOperationsService calculatorOperationsService;

	// Logger constant for logging details
	private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

	/**
	 * Purpose : REST API for Performing ADDITION operation
	 * 
	 * @param calculatorSvcRequest
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<CalculatorSvcResponse> additionOperationSvc(
			@Valid @RequestBody CalculatorSvcRequest calculatorSvcRequest) {
		logger.info("...Performing addition operation...");
		CalculatorSvcResponse calculatorSvcResponse = new CalculatorSvcResponse();
		return new ResponseEntity(calculatorOperationsService.add(calculatorSvcRequest, calculatorSvcResponse),
				HttpStatus.OK);
	}

	/**
	 * Purpose : REST API for Performing SUBTRACTION operation
	 * 
	 * @param calculatorSvcRequest
	 * @return
	 */
	@PostMapping("/subtract")
	public ResponseEntity<CalculatorSvcResponse> subtractOperationSvc(
			@Valid @RequestBody CalculatorSvcRequest calculatorSvcRequest) {
		logger.info("...Performing subtraction operation...");
		CalculatorSvcResponse calculatorSvcResponse = new CalculatorSvcResponse();
		return new ResponseEntity(calculatorOperationsService.subtract(calculatorSvcRequest, calculatorSvcResponse),
				HttpStatus.OK);
	}

	/**
	 * Purpose : REST API for Performing MUTLIPLICATION operation
	 * 
	 * @param calculatorSvcRequest
	 * @return
	 */
	@PostMapping("/multiply")
	@Validated(OnMultiplyAndDivide.class)
	public ResponseEntity<CalculatorSvcResponse> multiplyOperationSvc(
			@Valid @RequestBody CalculatorSvcRequest calculatorSvcRequest) {
		logger.info("...Performing multiplication operation...");
		CalculatorSvcResponse calculatorSvcResponse = new CalculatorSvcResponse();
		return new ResponseEntity(calculatorOperationsService.multiply(calculatorSvcRequest, calculatorSvcResponse),
				HttpStatus.OK);
	}

	/**
	 * Purpose : REST API for Performing DIVISION operation
	 * 
	 * @param calculatorSvcRequest
	 * @return
	 */
	@PostMapping("/divide")
	@Validated(OnMultiplyAndDivide.class)
	public ResponseEntity<CalculatorSvcResponse> divideOperationSvc(
			@Valid @RequestBody CalculatorSvcRequest calculatorSvcRequest) {
		logger.info("...Performing division operation...");
		CalculatorSvcResponse calculatorSvcResponse = new CalculatorSvcResponse();
		return new ResponseEntity(calculatorOperationsService.divide(calculatorSvcRequest, calculatorSvcResponse),
				HttpStatus.OK);
	}	
	
//	/**
//	 * Purpose : This method catch constraint violation exceptions and for the path variable variladtion if any.
//	 * Technical note : Uncommented this code 'Overrides' the @ControllerAdvice exception that is configured
//	 * @param exception
//	 * @return
//	 */
//	@ExceptionHandler(ConstraintViolationException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
//		logger.error("Exception Occured. Please check detailed log");
//		return new ResponseEntity<>("Validation error occured : " + exception.getMessage(), HttpStatus.BAD_REQUEST);
//	}
	
//	/**
//	 * Purpose : For handling all other exceptions that are occurring in this
//	 * controller
//	 */
//	@ExceptionHandler({Exception.class })
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	ResponseEntity<String> handleAllExceptions(Exception exception) {
//		logger.error("Exception Occured. Please check detailed log");
//		return new ResponseEntity<>("Error occured while processing the request : " + exception.getMessage(), HttpStatus.BAD_REQUEST);
//	}
}
