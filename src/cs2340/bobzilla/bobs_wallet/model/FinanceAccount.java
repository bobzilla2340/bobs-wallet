package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;
import java.util.ArrayList;

public class FinanceAccount implements Serializable {

	private static final long serialVersionUID = 8934355754572806709L;
	private String accountName;
	private double interestRate;
	private double currentBalance;
	
	private ArrayList<Transaction> withdrawals;
	private ArrayList<Transaction> deposits;
	private ArrayList<Transaction> transactions;
	
	public FinanceAccount(String accountName, double interest) {
		this.accountName = accountName;
		this.interestRate = interest;
		this.currentBalance = 0;
		withdrawals = new ArrayList<Transaction>();
		deposits = new ArrayList<Transaction>();
		transactions = new ArrayList<Transaction>();
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
		return accountName + "::" + currentBalance;
	}
	
	public void addTransaction(double amount, TransactionType type, String category) {
		transactions.add(new Transaction(amount, type, category));
		switch (type) {
		case DEPOSIT:
			addDeposit(amount, category);
			setCurrentBalance(this.currentBalance + amount);
			break;
		case WITHDRAWAL:
			addWithdrawal(amount, category);
			setCurrentBalance(this.currentBalance - amount);
		}
		
	} 
	
	private void addWithdrawal(double amount, String category) {
		withdrawals.add(new Transaction(amount, TransactionType.WITHDRAWAL, category));
	}
	
	private void addDeposit(double amount, String category) {
		deposits.add(new Transaction(amount, TransactionType.DEPOSIT, category));
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public ArrayList<Transaction> getWithdrawals() {
		return withdrawals;
	}
	
	public ArrayList<Transaction> getDeposits() {
		return deposits;
	}
	
	public void setCurrentBalance(double amount) {
		this.currentBalance = amount;
	}
	
	public void addToCurrentBalance(double amount) {
		this.currentBalance = currentBalance + amount;
	}
	
	public double getCurrentBalance() {
		return currentBalance;
	}
}
