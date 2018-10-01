package com.zycus.customExceptions;

public class NoRecordsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class clazz;

	public NoRecordsFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(String message, Class clazz) {
		super(message);

		this.clazz = clazz;
		// TODO Auto-generated constructor stub
	}

	public NoRecordsFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public Class getClazz() {
		return clazz;
	}

}
