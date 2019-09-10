package com.conciso.calculator.validator;

import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OperandsValidatorImpl implements ConstraintValidator<OperandsValidator, ArrayList<Integer>> {
	
	/**
	 * Purpose : Validating elements in input request to ensure all are numeric elements
	 * @param operands : refers to com.conciso.calculator.model.CalculatorSvcRequest.operands
	 * @param context
	 */
	@Override
	public boolean isValid(ArrayList<Integer> operands, ConstraintValidatorContext context) {
		boolean isOperandsNonZero = true;
		if(null != operands && operands.size()>0) {			
			for (Integer operand:operands) {
				if(operand == 0) {
					isOperandsNonZero = false;
				}
			}
		}
		return isOperandsNonZero;
	}

}
