package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Transaction represents a transaction for a User and contains an amount, a
 * type, a date, and a category.
 *
 * @author Farhan
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 7452030265735708570L;
    private double amount;
    private TransactionType type;
    private String date;
    public static final String DATE_FORMAT_PATTERN = "MM/dd/yy h:mm a";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN,
            Locale.getDefault());
    private String mCategory;

    /**
     * Creates a Transaction object given an amount, a type, and a category
     * 
     * @param amount
     *            , the double amount
     * @param type
     *            , the TransactionType type
     * @param category
     *            , the String category
     */
    public Transaction(double amount, TransactionType type, String category) {
        this.amount = amount;
        this.type = type;
        this.date = sdf.format(new Date());
        this.mCategory = category;
    }

    /**
     * Gets the transaction type
     * 
     * @return the TransactionType type
     */
    public TransactionType getTransactionType() {
        return type;
    }

    /**
     * Gets the transaction date
     * 
     * @return the String date
     */
    public String getTransactionDate() {
        return date;
    }

    /**
     * Gets the amount
     * 
     * @return the double amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the category
     * 
     * @return the String category
     */
    public String getCategory() {
        return mCategory;
    }
}
