package cs2340.bobzilla.bobs_wallet.model;

public class FinanceAccount {
	private String accountName;
	private double interestRate;
	
	public FinanceAccount(String accountName, double interest) {
		this.accountName = accountName;
		this.interestRate = interest;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setInterestRate(double interest) {
		this.interestRate = interest;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	@Override
	public String toString() {
		return accountName;
	}
}
