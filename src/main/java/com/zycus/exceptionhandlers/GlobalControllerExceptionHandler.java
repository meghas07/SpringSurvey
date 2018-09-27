package com.zycus.exceptionhandlers;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.zycus.customExceptions.EntityNotFoundInDatabaseException;

@EnableWebMvc
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(EntityNotFoundInDatabaseException.class)
	public String entityNotFoundInDatabaseException(HttpServletRequest request, EntityNotFoundInDatabaseException ex,
			Model model) {
		System.out.println("hello controller advice");
		System.out.println(ex.getMessage());
		model.addAttribute("errorMessage", ex.getMessage());
		return "error";
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 doubt? ask someone to confirm status code
	@ExceptionHandler(SQLException.class)
	public String couldNotPerformOperationException(HttpServletRequest request, SQLException ex) {

		return ex.getMessage();
	}

}
