package com.zycus.customExceptions;

public class EntityNotFoundInDatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Custom error occured";
	}
}
