package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;

import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;

/**
 * Created by Farhan on 3/30/14.
 */
public class AddTransactionTest extends TestCase {

    /**
     * This is the interest rate that is used in the finance account.
     */
    public static final double INTEREST_RATE = 3.2;
    /**
     * This is an amount that is used to test deposits in the testDeposit()
     * method.
     */
    public static final double TEST_DEPOSIT_AMOUNT1 = 500;
    /**
     * This is is the balance of the finance account that is used in the
     * testDeposit() method.
     */
    public static final double TEST_DEPOSIT_BALANCE1 = 500;
    /**
     * This is the amount that is withdrawn from the finance account in the
     * testWithdrawal() method.
     */
    public static final double TEST_WITHDRAWAL_AMOUNT1 = 350;
    /**
     * This is the amount that is in the finance account after withdrawal in the
     * testWithdrawal() method.
     */
    public static final double TEST_WITHDRAWAL_BALANCE1 = -350;

    /**
     * This method tests the deposit for the finance account.
     */
    public final void testDeposit() {
        FinanceAccount financeAccount = new FinanceAccount("test account",
                INTEREST_RATE);
        financeAccount.addTransaction(TEST_DEPOSIT_AMOUNT1,
                TransactionType.DEPOSIT, "Income");
        assertEquals(financeAccount.getDeposits().get(0).getCategory(),
                "Income");
        assertEquals(financeAccount.getCurrentBalance(), TEST_DEPOSIT_BALANCE1);
    }

    /**
     * This method tests the withdrawal for the finance account.
     */
    public final void testWithdrawal() {
        FinanceAccount financeAccount = new FinanceAccount("test account",
                INTEREST_RATE);
        financeAccount.addTransaction(TEST_WITHDRAWAL_AMOUNT1,
                TransactionType.WITHDRAWAL, "Toys");
        assertEquals(financeAccount.getWithdrawals().get(0).getCategory(),
                "Toys");
        assertEquals(financeAccount.getCurrentBalance(),
                TEST_WITHDRAWAL_BALANCE1);
    }

    /**
     * This method tests multiple deposits of the finance account.
     */
    public final void testMultDeposit() {
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(TEST_DEPOSIT_AMOUNT1,
                TransactionType.DEPOSIT, "Income");
        financeAccount.addTransaction(150, TransactionType.DEPOSIT, "Grandma");
        assertEquals(financeAccount.getDeposits().get(0).getAmount(), 500.0);
        assertEquals(financeAccount.getDeposits().get(1).getAmount(), 150.0);
        assertEquals(financeAccount.getCurrentBalance(), 650.0);
    }

    /**
     * This method tests multiple withdrawals of the finance account.
     */
    public final void testMultWithdrawal() {
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(350, TransactionType.WITHDRAWAL, "Toys");
        financeAccount.addTransaction(50, TransactionType.WITHDRAWAL,
                "Video Games");
        assertEquals(financeAccount.getWithdrawals().get(0).getAmount(), 350.0);
        assertEquals(financeAccount.getWithdrawals().get(1).getAmount(), 50.0);
        assertEquals(financeAccount.getCurrentBalance(), -400.0);
    }

    /**
     * This method tests multiple deposits and withdrawals on the finance
     * account.
     */
    public final void testDepositWithdrawal() {
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(500, TransactionType.DEPOSIT, "Income");
        financeAccount.addTransaction(50, TransactionType.WITHDRAWAL,
                "Video Games");
        assertEquals(financeAccount.getCurrentBalance(), 450.0);
        assertEquals(financeAccount.getTransactions().get(0).getAmount(), 500.0);
        assertEquals(financeAccount.getDeposits().get(0).getAmount(), 500.0);
        assertEquals(financeAccount.getTransactions().get(1).getAmount(), 50.0);
        assertEquals(financeAccount.getWithdrawals().get(0).getAmount(), 50.0);

    }

}
