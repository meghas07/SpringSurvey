package com.zycus.customExceptions;

public class CouldNotPerformOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object;

	public CouldNotPerformOperationException() {
		super();

	}

	public CouldNotPerformOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public CouldNotPerformOperationException(String message, Throwable cause) {
		super(message, cause);

	}

	public CouldNotPerformOperationException(String message, Object object) {
		super(message);
		this.object = object;

	}

	public CouldNotPerformOperationException(Throwable cause) {
		super(cause);

	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
