package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;

import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;

/**
 * This class tests the addTransaction method in FinanceAccount.
 * 
 * @author jack
 * 
 */
public class TransactionAddingTest extends TestCase {

    /**
     * This is the amount put into the finance account when testing the
     * withdrawal feature.
     */
    public static final int TEST_WITHDRAWAL_AMOUNT1 = 1332;
    /**
     * This is an amount that is used when testing the withdrawal feature.
     */
    public static final double TEST_WITHDRAWAL_AMOUNT2 = 100;
    /**
     * This is the amount that is balance of the finance account after
     * withdrawal amount 1.
     */
    public static final double TEST_WITHDRAWAL_BALANCE1 = -1332.0;
    /**
     * This is an amount that denotes the balance of the finance account after
     * withdrawal amount 2.
     */
    public static final double TEST_WITHDRAWAL_BALANCE2 = -131.0;
    /**
     * This is the amount that is initially placed in the finance account.
     */
    public static final double TEST_DEPOSIT_AMOUNT1 = 1331.0;
    /**
     * This is an amount that is used to test the deposit.
     */
    public static final double TEST_DEPOSIT_AMOUNT2 = 1301;
    /**
     * This is an amount that is used to test the deposit.
     */
    public static final double TEST_DEPOSIT_AMOUNT3 = 5;
    /**
     * This is the amount in the finance account after the deposit amount 1.
     */
    public static final double TEST_DEPOSIT_BALANCE1 = 1331.0;
    /**
     * This is the amount in the finance account after the deposit amount 2.
     */
    public static final double TEST_DEPOSIT_BALANCE2 = -31.0;
    /**
     * This is the amount in the finance account after the deposit amount 3.
     */
    public static final double TEST_DEPOSIT_BALANCE3 = -126.0;

    /**
     * This method will test the withdrawal feature of the finance account
     * class.
     * 
     */
    public final void testWithdrawal() {
        FinanceAccount testAccount = new FinanceAccount("testing", 0);
        testAccount.addTransaction(TEST_WITHDRAWAL_AMOUNT1,
                TransactionType.WITHDRAWAL, "fun");
        assertEquals(TEST_WITHDRAWAL_BALANCE1, testAccount.getCurrentBalance());
        assertEquals("testing", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getWithdrawals().size());

    }

    /**
     * This method will test the deposit feature of the finance account.
     */
    public final void testDeposit() {
        FinanceAccount testAccount = new FinanceAccount("testing again", 0);
        testAccount.addTransaction(TEST_DEPOSIT_AMOUNT1,
                TransactionType.DEPOSIT, "fun");
        assertEquals(TEST_DEPOSIT_BALANCE1, testAccount.getCurrentBalance());
        assertEquals("testing again", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getDeposits().size());

    }

    /**
     * This method tests both withdrawals and deposits.
     */
    public final void testBoth() {
        FinanceAccount testAccount = new FinanceAccount("testing", 0);
        testAccount.addTransaction(TEST_WITHDRAWAL_AMOUNT1,
                TransactionType.WITHDRAWAL, "fun");
        assertEquals(TEST_WITHDRAWAL_BALANCE1, testAccount.getCurrentBalance());
        assertEquals("testing", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getWithdrawals().size());
        testAccount.addTransaction(TEST_DEPOSIT_AMOUNT2,
                TransactionType.DEPOSIT, "fun");
        assertEquals(TEST_DEPOSIT_BALANCE2, testAccount.getCurrentBalance());
        assertEquals("testing", testAccount.getAccountName());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(TEST_WITHDRAWAL_AMOUNT2,
                TransactionType.WITHDRAWAL, "fun");
        assertEquals(TEST_WITHDRAWAL_BALANCE2, testAccount.getCurrentBalance());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(1, testAccount.getDeposits().size());
        testAccount.addTransaction(TEST_DEPOSIT_AMOUNT3,
                TransactionType.DEPOSIT, "fun");
        assertEquals(TEST_DEPOSIT_BALANCE3, testAccount.getCurrentBalance());
        assertEquals(0.0, testAccount.getInterestRate());
        assertEquals(2, testAccount.getWithdrawals().size());
        assertEquals(2, testAccount.getDeposits().size());
    }
}
