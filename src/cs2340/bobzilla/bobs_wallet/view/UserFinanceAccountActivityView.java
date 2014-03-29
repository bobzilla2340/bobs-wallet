package cs2340.bobzilla.bobs_wallet.view;

import cs2340.bobzilla.bobs_wallet.model.TransactionType;

public interface UserFinanceAccountActivityView {

	public String getTransactionAmount();
	
	public String getAccountName();
	
	public String getUsername();
	
	public String getCategory();
	
	public TransactionType getTransactionType();
	
}
