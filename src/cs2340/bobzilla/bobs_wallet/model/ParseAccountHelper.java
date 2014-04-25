package cs2340.bobzilla.bobs_wallet.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.parse.ParseObject;

public class ParseAccountHelper {
    public static void addParseAccountsToUser(List<ParseObject> parseAccounts) {
        FinanceAccount account;
        String accountName;
        User user = CurrentUser.getCurrentUser();
        
        Log.i("ParseAccountHelper", parseAccounts.toString() + FinanceAccount.PARSE_ACCOUNT_NAME_KEY);
        
        // Add each account in the parseAccounts list to the hashmap
        for (ParseObject parseAccount : parseAccounts) {
            accountName = parseAccount.getString(FinanceAccount.PARSE_ACCOUNT_NAME_KEY);
            account = transformToFinanceAccount(parseAccount);
            user.addExistingFinanceAccount(account);    
        }
        
    }
    
    private static FinanceAccount transformToFinanceAccount(ParseObject parseAccount) {
        // Retrieve values from parse objects
        String accountName = parseAccount.getString(FinanceAccount.PARSE_ACCOUNT_NAME_KEY);
        double interestRate = parseAccount.getDouble(FinanceAccount.PARSE_INTEREST_RATE_KEY);
        double balance = parseAccount.getDouble(FinanceAccount.PARSE_BALANCE_KEY);
        
        FinanceAccount account = new FinanceAccount(accountName, interestRate);
//        account.setCurrentBalance(balance); Adding transactions should set correct balance
        
        // Add transactions to account
        // Note: Transactions loaded from server when accounts were retrieved.
        ArrayList<ParseObject> transactions = (ArrayList<ParseObject>) parseAccount.get(FinanceAccount.PARSE_TRANSACTIONS_KEY);
        addParseTransactionsToFinanceAccount(transactions, account);
        
        return account;
    }
    
    private static void addParseTransactionsToFinanceAccount(List<ParseObject> parseTransactions, FinanceAccount account) {
        Transaction transaction;
        for (int i = 0; i < parseTransactions.size(); ++i) {
            transaction = transformToTransaction(parseTransactions.get(i));
            account.addTransaction(transaction);
        }
    }
    
    private static Transaction transformToTransaction(ParseObject parseTransaction) {
        // retrieve the values from parse objects
        double amount = parseTransaction.getDouble(Transaction.PARSE_AMOUNT_KEY);
        TransactionType type = getParseTransactionType(parseTransaction);
        String category = parseTransaction.getString(Transaction.PARSE_CATEGORY_KEY);
        String date = parseTransaction.getString(Transaction.PARSE_DATE_KEY);
        
        // Create new transaction
        Transaction transaction = new Transaction(amount, type, category);
        transaction.setDate(date);
        
        return transaction;
    }
    
    public static TransactionType getParseTransactionType(ParseObject transaction) {
        return TransactionType.valueOf(transaction.getString(Transaction.PARSE_TRANSACTION_TYPE_KEY));
    }
}
