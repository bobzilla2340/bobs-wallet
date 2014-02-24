package cs2340.bobzilla.bobs_wallet.model;

public class FinanceAccount {
	private String accountName;
	
	public FinanceAccount(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	@Override
	public String toString() {
		return accountName;
	}
}
