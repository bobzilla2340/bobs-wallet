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
        FinanceAccount testAccount = new FinanceAccount("testing",0);
        testAccount.addTransaction(1332,TransactionType.WITHDRAWAL,"fun");
        assertEquals(-1332.0,testAccount.getCurrentBalance());
        assertEquals("testing",testAccount.getAccountName());
        assertEquals(0.0,testAccount.getInterestRate());
        assertEquals(1, testAccount.getWithdrawals().size());

    }

    public void testDeposit() {
        FinanceAccount testAccount = new FinanceAccount("testing again",0);
        testAccount.addTransaction(1301,TransactionType.DEPOSIT,"fun");
        assertEquals(1301.0, testAccount.getCurrentBalance());
        assertEquals("testing again", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getDeposits().size());

    }

    public void testBoth() {
        FinanceAccount testAccount = new FinanceAccount("testing",0);
        testAccount.addTransaction(1332,TransactionType.WITHDRAWAL,"fun");
        assertEquals(-1332.0,testAccount.getCurrentBalance());
        assertEquals("testing",testAccount.getAccountName());
        assertEquals(0.0,testAccount.getInterestRate());
        assertEquals(1, testAccount.getWithdrawals().size());
        testAccount.addTransaction(1301,TransactionType.DEPOSIT,"fun");
        assertEquals(-31.0, testAccount.getCurrentBalance());
        assertEquals("testing again", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(100, TransactionType.WITHDRAWAL, "fun");
        assertEquals(-131.0, testAccount.getCurrentBalance());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(5, TransactionType.DEPOSIT, "fun");
        assertEquals(-126.0, testAccount.getCurrentBalance());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(2, testAccount.getDeposits().size());
    }
}
