package cs2340.bobzilla.bobs_wallet.test;

import junit.framework.TestCase;

import org.junit.*;

import cs2340.bobzilla.bobs_wallet.model.FinanceAccount;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;

/**
 * Created by Farhan on 3/30/14.
 */
public class AddTransactionTest extends TestCase{


    @Test
    public void testDeposit(){
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(500, TransactionType.DEPOSIT, "Income");
        assertEquals(financeAccount.getDeposits().get(0).getCategory(), "Income");
        assertEquals(financeAccount.getCurrentBalance(), 500.0);
    }

    @Test
    public void testWithdrawal(){
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(350, TransactionType.WITHDRAWAL, "Toys");
        assertEquals(financeAccount.getWithdrawals().get(0).getCategory(), "Toys");
        assertEquals(financeAccount.getCurrentBalance(), -350.0);
    }

    @Test
    public void testMultDeposit(){
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(500, TransactionType.DEPOSIT, "Income");
        financeAccount.addTransaction(150, TransactionType.DEPOSIT, "Grandma");
        assertEquals(financeAccount.getDeposits().get(0).getAmount(), 500.0);
        assertEquals(financeAccount.getDeposits().get(1).getAmount(), 150.0);
        assertEquals(financeAccount.getCurrentBalance(), 650.0);
    }

    @Test
    public void testMultWithdrawal(){
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(350, TransactionType.WITHDRAWAL, "Toys");
        financeAccount.addTransaction(50, TransactionType.WITHDRAWAL, "Video Games");
        assertEquals(financeAccount.getWithdrawals().get(0).getAmount(), 350.0);
        assertEquals(financeAccount.getWithdrawals().get(1).getAmount(), 50.0);
        assertEquals(financeAccount.getCurrentBalance(), -400.0);
    }

    @Test
    public void testDepositWithdrawal(){
        FinanceAccount financeAccount = new FinanceAccount("test account", 3.2);
        financeAccount.addTransaction(500, TransactionType.DEPOSIT, "Income");
        financeAccount.addTransaction(50, TransactionType.WITHDRAWAL, "Video Games");
        assertEquals(financeAccount.getCurrentBalance(), 450.0);
        assertEquals(financeAccount.getTransactions().get(0).getAmount(), 500.0);
        assertEquals(financeAccount.getDeposits().get(0).getAmount(), 500.0);
        assertEquals(financeAccount.getTransactions().get(1).getAmount(), 50.0);
        assertEquals(financeAccount.getWithdrawals().get(0).getAmount(), 50.0);

    }


}
