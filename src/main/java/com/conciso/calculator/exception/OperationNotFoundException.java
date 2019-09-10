package com.conciso.calculator.exception;
/**
 * Calculator application exceptions are defined here
 * @author rojan
 *
 */
public class OperationNotFoundException extends RuntimeException {
	//Todo Custom exceptions to be defined for large applications
	public OperationNotFoundException() {
        super("Invalid Operation Specified : Defined operation paths are "
        		+ "/add, /subtract, /divide and /multiply");
    }
}
