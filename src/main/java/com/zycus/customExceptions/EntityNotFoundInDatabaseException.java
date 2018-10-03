package com.zycus.customExceptions;

public class EntityNotFoundInDatabaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object;

	public EntityNotFoundInDatabaseException() {
		super();
	}

	public EntityNotFoundInDatabaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public EntityNotFoundInDatabaseException(String message, Throwable cause) {
		super(message, cause);

	}

	public EntityNotFoundInDatabaseException(String message, Object object) {
		super(message);
		this.setObject(object);

	}

	public EntityNotFoundInDatabaseException(Throwable cause) {
		super(cause);

	}

	@Override
	public synchronized Throwable getCause() {
		return super.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return super.initCause(cause);
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		super.setStackTrace(stackTrace);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
