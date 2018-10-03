package com.zycus.customExceptions;

public class InvalidDetailsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object object;

	public InvalidDetailsException() {
		super();

	}

	public InvalidDetailsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public InvalidDetailsException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidDetailsException(String message, Object object) {
		super(message);
		this.object = object;

	}

	public InvalidDetailsException(Throwable cause) {
		super(cause);

	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
