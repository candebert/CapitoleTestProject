package com.capitole.testProject.http.exception;

import org.springframework.http.HttpStatus;

public class InvalidDateFormat extends RuntimeException {

	public InvalidDateFormat(String applicationDate) {
		super("La fecha: " + applicationDate + " no respeta un patron valido. (yyyy-MM-dd HH:mm:ss)");
	}

	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
