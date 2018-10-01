package com.zycus.exceptionhandlers;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.customExceptions.NoRecordsFoundException;

@EnableWebMvc
@ControllerAdvice
public class GlobalControllerExceptionHandler {
	@Autowired
	private ModelAndView modelAndView;

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(EntityNotFoundInDatabaseException.class)
	public ModelAndView entityNotFoundInDatabaseException(HttpServletRequest request,
			EntityNotFoundInDatabaseException ex, Model model) {
		modelAndView.getModel().put("message", ex.getMessage());
		return new ModelAndView("/errorPages/error", modelAndView.getModel());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(NoRecordsFoundException.class)
	public ModelAndView noRecordFoundException(HttpServletRequest request, NoRecordsFoundException ex) {
		modelAndView.getModel().put("message", ex.getMessage());
		return new ModelAndView("/errorPages/error", modelAndView.getModel());

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 doubt? ask someone to confirm status code
	@ExceptionHandler(SQLException.class)
	public ModelAndView couldNotPerformOperationException(HttpServletRequest request, SQLException ex) {
		modelAndView.getModel().put("message", ex.getMessage());
		return new ModelAndView("/errorPages/error", modelAndView.getModel());

	}

}
