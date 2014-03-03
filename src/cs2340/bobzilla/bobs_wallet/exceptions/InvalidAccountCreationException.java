package cs2340.bobzilla.bobs_wallet.exceptions;

public class InvalidAccountCreationException extends Exception {

	public InvalidAccountCreationException() {
		super();
	}

	public InvalidAccountCreationException(String detailMessage) {
		super(detailMessage);
	}

	public InvalidAccountCreationException(Throwable throwable) {
		super(throwable);
	}

	public InvalidAccountCreationException(String detailMessage,
			Throwable throwable) {
		super(detailMessage, throwable);
	}

}
