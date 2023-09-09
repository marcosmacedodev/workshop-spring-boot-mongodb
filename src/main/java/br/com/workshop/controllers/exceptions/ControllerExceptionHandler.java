package br.com.workshop.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.workshop.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Long timestamp = System.currentTimeMillis();
		StandardError err = throwError(timestamp, status, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	private StandardError throwError(Long timestamp, HttpStatus status, String message, String path) {
		StandardError err = new StandardError();
		err.setTimestamp(System.currentTimeMillis());
		err.setStatus(status.value());
		err.setError(status.getReasonPhrase());
		err.setMessage(message);
		err.setPath(path);
		return err;
	}
}
