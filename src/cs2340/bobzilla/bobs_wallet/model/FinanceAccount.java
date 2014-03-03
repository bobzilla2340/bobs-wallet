package cs2340.bobzilla.bobs_wallet.model;

import java.util.ArrayList;

public class FinanceAccount {
	private String accountName;
	private double interestRate;
	private double currentBalance;
	
	private ArrayList<Double> withdrawals;
	private ArrayList<Double> deposits;
	private ArrayList<Double> transactions;
	
	public FinanceAccount(String accountName, double interest) {
		this.accountName = accountName;
		this.interestRate = interest;
		this.currentBalance = 0;
		withdrawals = new ArrayList<Double>();
		deposits = new ArrayList<Double>();
		transactions = new ArrayList<Double>();
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
	
	public void addTransaction(double amount) {
		transactions.add(amount);
	}
	
	public void addWithdrawal(double amount) {
		withdrawals.add(amount);
	}
	
	public void addDeposit(double amount) {
		deposits.add(amount);
	}
	
	public ArrayList<Double> getTransactions() {
		return transactions;
	}
	
	public ArrayList<Double> getWithdrawals() {
		return withdrawals;
	}
	
	public ArrayList<Double> getDeposits() {
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
