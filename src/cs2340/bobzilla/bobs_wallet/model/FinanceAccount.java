package cs2340.bobzilla.bobs_wallet.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * FinanceAccount represents an account created by a User. It has an
 * accountName, interestRate, currentBalance, list of transactions, list of
 * deposits, and a list of withdrawals.
 * 
 * @author Farhan
 */
public class FinanceAccount implements Serializable {

    private static final long serialVersionUID = 8934355754572806709L;
    private String accountName;
    private double interestRate;
    private double currentBalance;

    private ArrayList<Transaction> withdrawals;
    private ArrayList<Transaction> deposits;
    private ArrayList<Transaction> transactions;

    /**
     * Constructs a FinanceAccount with an accountName and an interest rate.
     * 
     * @param accountName
     *            , the String accountName
     * @param interest
     *            , the double interest
     */
    public FinanceAccount(String accountName, double interest) {
        this.accountName = accountName;
        this.interestRate = interest;
        this.currentBalance = 0;
        withdrawals = new ArrayList<Transaction>();
        deposits = new ArrayList<Transaction>();
        transactions = new ArrayList<Transaction>();
    }

    /**
     * Gets the accountName
     * 
     * @return accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the interest rate
     * 
     * @param interest
     *            , the double interest rate
     */
    public void setInterestRate(double interest) {
        this.interestRate = interest;
    }

    /**
     * Gets the interest rate
     * 
     * @return interestRate
     */
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return accountName + "::" + currentBalance;
    }

    /**
     * Adds a transaction given an amount, a type, and a category.
     * 
     * @param amount
     *            , the double amount
     * @param type
     *            , the TransactionType type
     * @param category
     *            , the String category
     */
    public void addTransaction(double amount, TransactionType type,
            String category) {
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

    /**
     * Adds a withdrawal to the withdrawal list given an amount and category
     * 
     * @param amount
     *            , the double amount
     * @param category
     *            , the String category
     */
    private void addWithdrawal(double amount, String category) {
        withdrawals.add(new Transaction(amount, TransactionType.WITHDRAWAL,
                category));
    }

    /**
     * Adds a deposit to the deposit list given an amount and category
     * 
     * @param amount
     *            , the double amount
     * @param category
     *            , the String category
     */
    private void addDeposit(double amount, String category) {
        deposits.add(new Transaction(amount, TransactionType.DEPOSIT, category));
    }

    /**
     * Gets the transaction list
     * 
     * @return transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Gets the withdrawal list
     * 
     * @return withdrawals
     */
    public ArrayList<Transaction> getWithdrawals() {
        return withdrawals;
    }

    /**
     * Gets the deposit list
     * 
     * @return deposits
     */
    public ArrayList<Transaction> getDeposits() {
        return deposits;
    }

    /**
     * Sets the currentBalance to amount
     * 
     * @param amount
     *            , the double amount
     */
    public void setCurrentBalance(double amount) {
        this.currentBalance = amount;
    }

    /**
     * Adds amount to the currentBalance
     * 
     * @param amount
     *            , the double amount
     */
    public void addToCurrentBalance(double amount) {
        this.currentBalance = currentBalance + amount;
    }

    /**
     * Gets the currentBalance
     * 
     * @return currentBalance
     */
    public double getCurrentBalance() {
        return currentBalance;
    }
}
