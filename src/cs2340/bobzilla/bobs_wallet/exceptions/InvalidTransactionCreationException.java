package cs2340.bobzilla.bobs_wallet.exceptions;

/**
 * InvalidTransactionCreationException handles invalid transaction creations.
 * @author Farhan
 */
public class InvalidTransactionCreationException extends Exception {

	private static final long serialVersionUID = -4660219679389201173L;

    /**
     * Throws an InvalidTransactionCreationException
     */
	public InvalidTransactionCreationException() {
		super();
	}

    /**
     * Throws an InvalidTransactionCreationException with a message
     * @param detailMessage, a message
     */
	public InvalidTransactionCreationException(String detailMessage) {
		super(detailMessage);
	}

    /**
     * Throws an InvalidTransactionCreationException with a Throwable object
     * @param throwable, a Throwable object
     */
	public InvalidTransactionCreationException(Throwable throwable) {
		super(throwable);
	}

    /**
     * Throws an InvalidTransactionCreationException with a message and
     * Throwable object.
     * @param detailMessage, a message
     * @param throwable, a Throwable object
     */
	public InvalidTransactionCreationException(String detailMessage,
			Throwable throwable) {
		super(detailMessage, throwable);
	}

}
