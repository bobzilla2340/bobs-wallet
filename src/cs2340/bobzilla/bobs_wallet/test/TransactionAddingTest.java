package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;


import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;

/**
 * This class tests the addTransaction method in FinanceAccount.
 * @author jack
 *
 */
public class TransactionAddingTest extends TestCase {

    public void testWithdrawal() {
        FinanceAccount testAccount = new FinanceAccount("test",0);
        testAccount.addTransaction(2340,TransactionType.WITHDRAWAL,"fun");
        assertEquals(-2340.0,testAccount.getCurrentBalance());
        assertEquals("test",testAccount.getAccountName());
        assertEquals(0.0,testAccount.getInterestRate());
        assertEquals(1, testAccount.getWithdrawals().size());

    }

    public void testDeposit() {
        FinanceAccount testAccount = new FinanceAccount("test",0);
        testAccount.addTransaction(2340,TransactionType.DEPOSIT,"fun");
        assertEquals(2340.0, testAccount.getCurrentBalance());
        assertEquals("test", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getDeposits().size());

    }

    public void testBoth() {
        FinanceAccount testAccount = new FinanceAccount("test",0);
        testAccount.addTransaction(2340,TransactionType.WITHDRAWAL,"fun");
        assertEquals(-2340.0, testAccount.getCurrentBalance());
        assertEquals(1, testAccount.getWithdrawals().size());
        assertEquals(0, testAccount.getDeposits().size());
        testAccount.addTransaction(2440, TransactionType.DEPOSIT, "fun");
        assertEquals(100.0, testAccount.getCurrentBalance());
        assertEquals(1, testAccount.getWithdrawals().size());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(100, TransactionType.WITHDRAWAL, "fun");
        assertEquals(0.0, testAccount.getCurrentBalance());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(5, TransactionType.DEPOSIT, "fun");
        assertEquals(5.0, testAccount.getCurrentBalance());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(2, testAccount.getDeposits().size());
    }
}
