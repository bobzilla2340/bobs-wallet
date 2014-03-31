package cs2340.bobzilla.bobs_wallet.view;

import cs2340.bobzilla.bobs_wallet.model.TransactionType;

public interface UserFinanceAccountActivityView {

	/**
	 * This method returns the transaction that the
	 * use wants to place on their financial account.
	 * @return String amount The amount the user wants
	 * to withdraw or deposit.
	 */
	public String getTransactionAmount();
	
	/**
	 * This method returns the account name the user
	 * wants to open.
	 * @return String name The name of the account the
	 * user wants to inspect
	 */
	public String getAccountName();
	
	/**
	 * This method returns the username that is associated
	 * with the account.
	 * @return String userName The username that the account
	 * is associated with.
	 */
	public String getUsername();
	
	/**
	 * This method returns the category of the transaction.
	 * 
	 * @return String category The type of transaction.
	 */
	public String getCategory();
	
	/**
	 * This method returns whether the transaction was
	 * a deposit or a withdrawal.
	 * 
	 * @return TransactionType type Indicates if the
	 * transaction was a deposit or a withdrawal.
	 */
	public TransactionType getTransactionType();
	
}
