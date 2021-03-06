package com.zycus.customExceptions;

public class NoRecordsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object;

	public NoRecordsFoundException() {
		super();

	}

	public NoRecordsFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public NoRecordsFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public NoRecordsFoundException(String message, Object object) {
		super(message);

		this.setObject(object);

	}

	public NoRecordsFoundException(Throwable cause) {
		super(cause);

	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
