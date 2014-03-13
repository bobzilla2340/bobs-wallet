package cs2340.bobzilla.bobs_wallet.activites;

import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public class UserFinanceAccountActivity extends Activity implements UserFinanceAccountActivityView {

	private TextView accountNameTextView;
	private TextView accountBalanceTextView;
	private EditText transactionAmountEditText;
	
	private UserFinanceAccountActivityPresenter financeAccountPresenter;
	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	
	private String financeAccountName;
	private String userName;
	
	private TransactionType mTransactionType;
	private Spinner mSpinner;
	private String mSelectedCategory;
	
	public Date startDate;
	public Date endDate;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_finance_account);
		
		Intent userFinanceAccountIntent = getIntent();
		financeAccountName = userFinanceAccountIntent.getStringExtra(UserAccountActivity.USER_FINANCE_ACCOUNT_NAME);
		userName = userFinanceAccountIntent.getStringExtra(UserAccountActivity.USER_NAME);

		financeAccountPresenter = new UserFinanceAccountActivityPresenter(this);
		
		listView = (ListView)findViewById(R.id.userTransactionAccountList);
		accountNameTextView = (TextView)findViewById(R.id.userFinanceAccountActivityAccountNameTextView);
		accountBalanceTextView = (TextView)findViewById(R.id.userFinanceAccountActivityBalanceValueTextView);
		accountNameTextView.append(" " + financeAccountName);
		accountBalanceTextView.append(" " + financeAccountPresenter
				.getFormattedCurrentAccountBalance(financeAccountName));

		arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.user_finance_account_list_text_view);
		listView.setAdapter(arrayAdapter);
		
		for (String transactions : financeAccountPresenter.getFormattedTransactions(userName, financeAccountName)) {
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
	
	public void onClick(View view) {
		LayoutInflater inflater = UserFinanceAccountActivity.this.getLayoutInflater();
		final View alertDialogView = inflater.inflate(R.layout.alert_dialog_user_finance_account_transaction, null);
		AlertDialog.Builder alert = new AlertDialog.Builder(this)
		.setTitle(R.string.user_account_finance_transaction_create_dialog_box)
		.setNegativeButton("Cancel", null)
		.setPositiveButton("Create", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				mSelectedCategory = mSpinner.getSelectedItem().toString();
				createTransaction(mTransactionType);
			}
		})
		.setView(alertDialogView);
		
		mSpinner = (Spinner) alertDialogView.findViewById(R.id.categories_spinner);
		mSpinner.setAdapter(createAdapter(TransactionType.DEPOSIT));
		
		alert.show();
		transactionAmountEditText = (EditText)alertDialogView.findViewById(R.id.alertDialogUserFinanceAccountTransactionEditText);
	}
	
	/**
	 * Based on the type of transaction chosen (withdrawal or deposit),
	 * the category choices for the transaction (viewable and selectable
	 * through the spinner) is changed.
	 * @param view
	 */
	public void onTransactionTypeClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		
		switch(view.getId()) {
		case R.id.createDepositRadioButton:
			if(checked) {
				mTransactionType = TransactionType.DEPOSIT;
				mSpinner.setAdapter(createAdapter(TransactionType.DEPOSIT));
			}
			break;
		case R.id.createWithdrawalRadioButton:
			if(checked) {
				mTransactionType = TransactionType.WITHDRAWAL;
				mSpinner.setAdapter(createAdapter(TransactionType.WITHDRAWAL));
			}
			break;
		}
	}
	
	/**
	 * ArrayAdapter created based on the transaction type
	 * Used in onTransactionTypeClicked method
	 * @param type
	 * @return
	 */
	public ArrayAdapter<CharSequence> createAdapter(TransactionType type) {
		ArrayAdapter<CharSequence> adapter = null;
		switch(type) {
		case DEPOSIT:
			adapter = ArrayAdapter.createFromResource(this, R.array.deposit_categories, android.R.layout.simple_spinner_item);
			break;
		case WITHDRAWAL:
			adapter = ArrayAdapter.createFromResource(this, R.array.withdrawal_categories, android.R.layout.simple_spinner_item);
			break;
		}
		return adapter;
	}
	
	/*
	 * Adds a transaction based on the type (Withdrawal/Deposit) passed in.
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
			arrayAdapter.add(typeSymbol + " \t +$" + transactionAmountEditText.getText().toString() + "\t\t" 
					+ mSelectedCategory + "\t\t just now");
			accountBalanceTextView.setText(" " + financeAccountPresenter
					.getFormattedCurrentAccountBalance(financeAccountName));
			Toast.makeText(UserFinanceAccountActivity.this, transactionName + " successfully added!", 
					Toast.LENGTH_SHORT).show();
		} catch (InvalidTransactionCreationException e) {
			String toastMessage = e.getMessage();
			Toast.makeText(UserFinanceAccountActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
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
