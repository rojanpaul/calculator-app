package com.conciso.calculator.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.conciso.calculator.validator.OperandsValidator;
import com.conciso.calculator.validator.model.OnMultiplyAndDivide;

/**
 * Calculator Application input request is defined here
 * 
 * @author rojan
 *
 */
public class CalculatorSvcRequest {

	private String operationName;

	@OperandsValidator(groups = OnMultiplyAndDivide.class)
	@NotNull(message = "Operands should not be null")
	@NotEmpty(message = "Operands should not be empty")
	private ArrayList<Integer> operands = null;//Technical note :  jsr 303 doesn#t provide direct support to list

	public ArrayList<Integer> getOperands() {
		return operands;
	}

	public void setOperands(ArrayList<Integer> operands) {
		this.operands = operands;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

}
