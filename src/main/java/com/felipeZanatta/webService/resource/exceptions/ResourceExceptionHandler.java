package com.felipeZanatta.webService.resource.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.felipeZanatta.webService.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


// @ExceptionHandler vai interceptar qualquer exceção do tipo entre parenteses, neste caso nossa exceção personalizada
// o status.value é para passar para inteiro
// o status do ResponseEntity do return é para retornar a resposta com cód personalizada
// o body é o corpo da resposta




@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resorce not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}

}
