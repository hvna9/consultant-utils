package com.github.h9lib.consultantutils.exceptions.annotations;

/**
 * Exception to report the missing of an annotation where it is needed.
 */
public final class MissingAnnotationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message the exception message
	 */
	public MissingAnnotationException(String message) {
        super(message);
    }
}
