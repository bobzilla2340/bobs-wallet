package cs2340.bobzilla.bobs_wallet.exceptions;

public class InvalidReportCreationException extends Exception {
	public InvalidReportCreationException() {
		super();
	}
	
	public InvalidReportCreationException(String message) {
		super(message);
	}
	
	public InvalidReportCreationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidReportCreationException(Throwable message) {
		super(message);
	}

}
