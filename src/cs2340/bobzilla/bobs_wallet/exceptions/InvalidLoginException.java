package cs2340.bobzilla.bobs_wallet.exceptions;

/**
 * InvalidLoginException handles invalid logins
 *
 * @author Farhan
 */

public class InvalidLoginException extends Exception {

	private static final long serialVersionUID = -3467139605660047459L;

    /**
     * Throws an InvalidLoginException.
     */
	public InvalidLoginException() {
		super();
	}

    /**
     * Throws an InvalidLoginException with a message.
     * @param message, a String message
     */
	public InvalidLoginException(String message) {
		super(message);
	}

    /**
     * Throws an InvalidException with a message and cause
     * @param message, a String message
     * @param cause, a Throwable cause
     */
	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}

    /**
     * Throws an InvalidLoginException with a cause
     * @param cause, a Throwable cause
     */
	public InvalidLoginException(Throwable cause) {
		super(cause);
	}
}
