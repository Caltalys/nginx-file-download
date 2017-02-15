package com.example.web.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.common.HttpResponseTool;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	public void handle(HttpServletResponse response, Exception e) {	// "request" is unused
		logger.warn(StringUtils.EMPTY, e);
		String message = e.getMessage();
		HttpResponseTool.writeInternalServerError(response, null, message, null);
	}

}
