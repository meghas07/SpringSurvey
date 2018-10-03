package com.zycus.customExceptions;

public class IncompleteDetailsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object object;

	public IncompleteDetailsException() {
		super();

	}

	public IncompleteDetailsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public IncompleteDetailsException(String message, Throwable cause) {
		super(message, cause);

	}

	public IncompleteDetailsException(String message, Object object) {
		super(message);
		this.setObject(object);
		System.out.println("eher");

	}

	public IncompleteDetailsException(Throwable cause) {
		super(cause);

	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
