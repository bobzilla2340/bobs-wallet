package cs2340.bobzilla.bobs_wallet.activites;

import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidTransactionCreationException;
import cs2340.bobzilla.bobs_wallet.model.TransactionType;
import cs2340.bobzilla.bobs_wallet.presenter.UserFinanceAccountActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.UserFinanceAccountActivityView;

/**
 * This activity allows the user to view all of the deposits
 * and transactions for a particular finance account.
 * @author sai
 *
 */
public class UserFinanceAccountActivity extends Activity implements
        UserFinanceAccountActivityView {

    /**
     * This is the name of the account.
     */
    private TextView accountNameTextView;
    /**
     * This is the balance of the account balance.
     */
    private TextView accountBalanceTextView;
    /**
     * This is the amount of transactions in the
     * account.
     */
    private EditText transactionAmountEditText;
    /**
     * This is the reference to the presenter for this activity.
     */
    private UserFinanceAccountActivityPresenter financeAccountPresenter;
    /**
     * This is used to disipaly the list of transactions.
     */
    private ListView listView;
    /**
     * This stores the actual transactions.
     */
    private ArrayAdapter<String> arrayAdapter;
    /**
     * This is the name of the finance account.
     */
    private String financeAccountName;
    /**
     * This is the username.
     */
    private String userName;
    /**
     * This is the transaction type.
     */
    private TransactionType mTransactionType;
    /**
     * This is the spinner.
     */
    private Spinner mSpinner;
    /**
     * This is the category.
     */
    private String mSelectedCategory;
    /**
     * Start date of the report.
     */
    public Date startDate;
    /**
     * End date of the report.
     */
    public Date endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_finance_account);

        Intent userFinanceAccountIntent = getIntent();
        financeAccountName = userFinanceAccountIntent
                .getStringExtra(UserAccountActivity.USER_FINANCE_ACCOUNT_NAME);
        userName = userFinanceAccountIntent
                .getStringExtra(UserAccountActivity.USER_NAME);

        financeAccountPresenter = new UserFinanceAccountActivityPresenter(this);

        listView = (ListView) findViewById(R.id.userTransactionAccountList);
        accountNameTextView = (TextView) findViewById(R.id.userFinanceAccountActivityAccountNameTextView);
        accountBalanceTextView = (TextView) findViewById(R.id.userFinanceAccountActivityBalanceValueTextView);
        accountNameTextView.append(" " + financeAccountName);
        accountBalanceTextView.append(" "
                + financeAccountPresenter
                        .getFormattedCurrentAccountBalance(financeAccountName));

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.user_finance_account_list_text_view);
        listView.setAdapter(arrayAdapter);

        for (String transactions : financeAccountPresenter
                .getFormattedTransactions(userName, financeAccountName)) {
            arrayAdapter.add(transactions);
        }
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.e("ReportActivity", "About to navigate up.");
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method reacts to when the user wants to make a
     * new transaction.
     * @param view
     *          The view to be modified when the user
     *          wants to add a transaction.
     */
    public void onClick(View view) {
        LayoutInflater inflater = UserFinanceAccountActivity.this
                .getLayoutInflater();
        final View alertDialogView = inflater.inflate(
                R.layout.alert_dialog_user_finance_account_transaction, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle(
                        R.string.user_account_finance_transaction_create_dialog_box)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Create",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                mSelectedCategory = mSpinner.getSelectedItem()
                                        .toString();
                                createTransaction(mTransactionType);
                            }
                        }).setView(alertDialogView);

        mSpinner = (Spinner) alertDialogView
                .findViewById(R.id.categories_spinner);
        mSpinner.setAdapter(createAdapter(TransactionType.DEPOSIT));

        alert.show();
        transactionAmountEditText = (EditText) alertDialogView
                .findViewById(R.id.alertDialogUserFinanceAccountTransactionEditText);
    }

    /**
     * Based on the type of transaction chosen (withdrawal or deposit), the
     * category choices for the transaction (viewable and selectable through the
     * spinner) is changed.
     * 
     * @param view
     *          The view that is changed when a transaction is chosen.
     */
    public void onTransactionTypeClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.createDepositRadioButton:
                if (checked) {
                    mTransactionType = TransactionType.DEPOSIT;
                    mSpinner.setAdapter(createAdapter(TransactionType.DEPOSIT));
                }
                break;
            case R.id.createWithdrawalRadioButton:
                if (checked) {
                    mTransactionType = TransactionType.WITHDRAWAL;
                    mSpinner.setAdapter(createAdapter(TransactionType.WITHDRAWAL));
                }
                break;
        }
    }

    /**
     * ArrayAdapter created based on the transaction type Used in.
     * onTransactionTypeClicked method
     * 
     * @param type
     *          The type of the transaction.
     * @return
     *          The adapter to be displayed in the list view.
     */
    public ArrayAdapter<CharSequence> createAdapter(TransactionType type) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (type) {
            case DEPOSIT:
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.deposit_categories,
                        android.R.layout.simple_spinner_item);
                break;
            case WITHDRAWAL:
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.withdrawal_categories,
                        android.R.layout.simple_spinner_item);
                break;
        }
        return adapter;
    }

    /**
     * Adds a transaction based on the type (Withdrawal/Deposit) passed in.
     * 
     * @param type
     *          The type of the transaction.
     */
    public void createTransaction(TransactionType type) {
        String typeSymbol = "";
        String transactionName = "";

        switch (type) {
            case DEPOSIT:
                typeSymbol = "D";
                transactionName = "Deposit";
                break;
            case WITHDRAWAL:
                typeSymbol = "W";
                transactionName = "Withdrawal";
                break;
        }
        try {
            // Calls presenter's onClick method to make proper changes to model
            financeAccountPresenter.onClick();

            // Modifies screen to show updated information
            arrayAdapter.add(typeSymbol + " \t +$"
                    + transactionAmountEditText.getText().toString() + "\t\t"
                    + mSelectedCategory + "\t\t just now");
            accountBalanceTextView
                    .setText(" "
                            + financeAccountPresenter
                                    .getFormattedCurrentAccountBalance(financeAccountName));
            Toast.makeText(UserFinanceAccountActivity.this,
                    transactionName + " successfully added!",
                    Toast.LENGTH_SHORT).show();
        } catch (InvalidTransactionCreationException e) {
            String toastMessage = e.getMessage();
            Toast.makeText(UserFinanceAccountActivity.this, toastMessage,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getTransactionAmount() {
        return transactionAmountEditText.getText().toString();
    }

    @Override
    public String getAccountName() {
        return financeAccountName;
    }

    @Override
    public String getCategory() {
        return mSelectedCategory;
    }

    @Override
    public TransactionType getTransactionType() {
        return mTransactionType;
    }

}
