package cs2340.bobzilla.bobs_wallet.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	private double amount;
	private TransactionType type;
	private String date;
	public static final String DATE_FORMAT_PATTERN = "MM/dd/yy h:mm a";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);

	
	public Transaction(double amount, TransactionType type) {
		this.amount = amount;
		this.type = type;
		this.date = sdf.format(new Date());
	}
	
	public TransactionType getTransactionType() {
		return type;
	}
	
	public String getTransactionDate() {
		return date;
	}
	
	public double getAmount() {
		return amount;
	}
}
