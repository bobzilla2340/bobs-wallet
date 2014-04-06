package cs2340.bobzilla.bobs_wallet.exceptions;

/**
 * A class to handle invalid report creations.
 * 
 * @author Farhan
 */
public class InvalidReportCreationException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6827537851039920508L;

    /**
     * Throws an InvalidReportCreation.
     */
    public InvalidReportCreationException() {
        super();
    }

    /**
     * Throws an InvalidReportCreation with a message.
     * 
     * @param message
     *            , a String message
     */
    public InvalidReportCreationException(String message) {
        super(message);
    }

    /**
     * Throws an InvalidReportCreationException wih a message and cause.
     * 
     * @param message
     *            , a String message
     * @param cause
     *            , a Throwable cause
     */
    public InvalidReportCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Throws an InvalidReportCreationException with a cause.
     * 
     * @param cause
     *            , a Throwable cause
     */
    public InvalidReportCreationException(Throwable cause) {
        super(cause);
    }

}
