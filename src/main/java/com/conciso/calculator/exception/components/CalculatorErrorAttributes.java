package com.conciso.calculator.exception.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CalculatorErrorAttributes extends DefaultErrorAttributes {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * Purpose : This method is responsible for overriding the default
	 * getErrorAttributes methods for exception.
	 * Technical Note : HttpServletResponse.sendError() : error message can be customized here
	 * CalculationApplicationExceptionHandler.handleConstraintViolationException
	 */
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		// Reusing default implementation & removing stack trace from response for
		// better readability
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, false);
		Object timestamp = errorAttributes.get("timestamp");
		if (timestamp == null) {
			errorAttributes.put("timestamp", dateFormat.format(new Date()));
		} else {
			errorAttributes.put("timestamp", dateFormat.format((Date) timestamp));
		}
		return errorAttributes;

	}
}
