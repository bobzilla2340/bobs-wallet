package cs2340.bobzilla.bobs_wallet.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

public class ParseAccountHelper {
    public static void addParseAccountsToUser(List<ParseObject> parseAccounts) {
//        HashMap<String, FinanceAccount> accounts = new HashMap<String, FinanceAccount>();
        FinanceAccount account;
        String accountName;
        User user = CurrentUser.getCurrentUser();
        
        Log.i("ParseAccountHelper", parseAccounts.toString() + FinanceAccount.PARSE_ACCOUNT_NAME_KEY);
        
        // Add each account in the parseAccounts list to the hashmap
        for (ParseObject parseAccount : parseAccounts) {
            accountName = parseAccount.getString(FinanceAccount.PARSE_ACCOUNT_NAME_KEY);
            account = transformToFinanceAccount(parseAccount);
            user.addExistingFinanceAccount(account);
//            accounts.put(accountName, account);        
        }
        
//        return accounts;
    }
    
    private static FinanceAccount transformToFinanceAccount(ParseObject parseAccount) {
        // Retrieve values from parse objects
        String accountName = parseAccount.getString(FinanceAccount.PARSE_ACCOUNT_NAME_KEY);
        double interestRate = parseAccount.getDouble(FinanceAccount.PARSE_INTEREST_RATE_KEY);
        double balance = parseAccount.getDouble(FinanceAccount.PARSE_BALANCE_KEY);
        
        FinanceAccount account = new FinanceAccount(accountName, interestRate);
        account.setCurrentBalance(balance);
        
        // TODO: Transactions
        
        return account;
    }
}
