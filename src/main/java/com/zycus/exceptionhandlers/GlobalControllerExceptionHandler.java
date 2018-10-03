package com.zycus.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zycus.customExceptions.CouldNotPerformOperationException;
import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.customExceptions.IncompleteDetailsException;
import com.zycus.customExceptions.InvalidDetailsException;
import com.zycus.customExceptions.NoRecordsFoundException;
import com.zycus.errorDescription.ErrorDetails;

@EnableWebMvc
@ControllerAdvice
@RequestMapping(produces = "application/json")
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidDetailsException.class)
	public ResponseEntity<String> invalidDetailsException(InvalidDetailsException ex) {
		System.out.println(ex.getMessage());
		ErrorDetails details = new ErrorDetails(ex.getMessage(), ex.getObject());

		return new ResponseEntity<String>(details.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IncompleteDetailsException.class)
	public ResponseEntity incompleteDetailsException(IncompleteDetailsException ex) {
		System.out.println(ex.getMessage());
		ErrorDetails details = new ErrorDetails(ex.getMessage(), ex.getObject());
		return new ResponseEntity(details.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundInDatabaseException.class)
	public ResponseEntity<String> entityNotFoundInDatabaseException(EntityNotFoundInDatabaseException ex) {
		System.out.println(ex.getMessage());
		ErrorDetails details = new ErrorDetails(ex.getMessage(), ex.getObject());

		return new ResponseEntity<String>(details.getErrorMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoRecordsFoundException.class)
	public ResponseEntity<ErrorDetails> noRecordFoundException(NoRecordsFoundException ex) {
		ErrorDetails details = new ErrorDetails(ex.getMessage(), ex.getObject());
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CouldNotPerformOperationException.class)
	public ResponseEntity<String> couldNotPerformOperationException(CouldNotPerformOperationException ex) {
		ErrorDetails details = new ErrorDetails(ex.getMessage(), ex.getObject());
		return new ResponseEntity<String>(details.getErrorMessage(), HttpStatus.BAD_REQUEST);

	}

}
