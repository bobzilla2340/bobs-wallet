package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;

import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;

/**
 * This class will test the addTransaction method in FinanceAccount.
 * 
 * @author dale
 * 
 */
public class TransactionTest extends TestCase {
    /**
     * Name of the finance account.
     */
    private String accountName = "test";
    /**
     * A transaction category.
     */
    private String category = "fun";
    /**
     * This function tests the withdrawal.
     */
    public void testWithdrawal() {
        FinanceAccount testAccount = new FinanceAccount(accountName, 0);
        testAccount.addTransaction(2340, TransactionType.WITHDRAWAL, category);
        assertEquals(-2340.0, testAccount.getCurrentBalance());
        assertEquals("test", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getWithdrawals().size());

    }

    /**
     * This tests the deposits as well as the withdrawals.
     */
    public void testDeposit() {
        FinanceAccount testAccount = new FinanceAccount(accountName, 0);
        testAccount.addTransaction(2340, TransactionType.DEPOSIT, category);
        assertEquals(2340.0, testAccount.getCurrentBalance());
        assertEquals("test", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getDeposits().size());

    }

    /**
     * This tests both the withdrawals and the deposits.
     */
    public void testBoth() {
        FinanceAccount testAccount = new FinanceAccount(accountName, 0);
        testAccount.addTransaction(2340, TransactionType.WITHDRAWAL, category);
        assertEquals(-2340.0, testAccount.getCurrentBalance());
        assertEquals(1, testAccount.getWithdrawals().size());
        assertEquals(0, testAccount.getDeposits().size());
        testAccount.addTransaction(2440, TransactionType.DEPOSIT, category);
        assertEquals(100.0, testAccount.getCurrentBalance());
        assertEquals(1, testAccount.getWithdrawals().size());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(100, TransactionType.WITHDRAWAL, category);
        assertEquals(0.0, testAccount.getCurrentBalance());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(5, TransactionType.DEPOSIT, category);
        assertEquals(5.0, testAccount.getCurrentBalance());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(2, testAccount.getDeposits().size());
    }
}
