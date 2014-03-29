package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 7452030265735708570L;
	private double amount;
	private TransactionType type;
	private String date;
	public static final String DATE_FORMAT_PATTERN = "MM/dd/yy h:mm a";
	private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
	private String mCategory;

	
	public Transaction(double amount, TransactionType type, String category) {
		this.amount = amount;
		this.type = type;
		this.date = sdf.format(new Date());
		this.mCategory = category;
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
	
	public String getCategory() {
		return mCategory;
	}
}
