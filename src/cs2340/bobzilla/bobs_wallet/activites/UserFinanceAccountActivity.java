package cs2340.bobzilla.bobs_wallet.activites;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidTransactionCreationException;
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
	
	private static final DecimalFormat df = new DecimalFormat("$###,##0.00");
	
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
		accountBalanceTextView.append(" " + df.format(financeAccountPresenter.getCurrentAccountBalance(financeAccountName)));

		arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.user_finance_account_list_text_view);
		listView.setAdapter(arrayAdapter);
		
		for (Double value : financeAccountPresenter.getTransactions(userName, financeAccountName)) {
			arrayAdapter.add(value.toString());
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
		new AlertDialog.Builder(this)
		.setTitle(R.string.user_account_finance_transaction_create_dialog_box)
		.setNegativeButton("Cancel", null)
		.setNeutralButton("Withdraw", new AlertDialogWithdrawalClickListener())
		.setPositiveButton("Deposit", new AlertDialogDepositClickListener())
		.setView(alertDialogView)
		.show();
		transactionAmountEditText = (EditText)alertDialogView.findViewById(R.id.alertDialogUserFinanceAccountTransactionEditText);
	}
	
	private class AlertDialogWithdrawalClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			
			try {
				financeAccountPresenter.onClickWithdrawal();
				arrayAdapter.add("Withdrawal: \t -" + transactionAmountEditText.getText().toString());
				accountBalanceTextView.setText(" " + df.format(
						financeAccountPresenter.getCurrentAccountBalance(financeAccountName)));
				Toast.makeText(UserFinanceAccountActivity.this, "Withdrawal successfully added!", 
						Toast.LENGTH_SHORT).show();
			} catch (InvalidTransactionCreationException e) {
				String toastMessage = e.getMessage();
				Toast.makeText(UserFinanceAccountActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private class AlertDialogDepositClickListener implements DialogInterface.OnClickListener {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			try {
				financeAccountPresenter.onClick();
				arrayAdapter.add("Deposit: \t +" + transactionAmountEditText.getText().toString());
				accountBalanceTextView.setText(" " + df.format(
						financeAccountPresenter.getCurrentAccountBalance(financeAccountName)));
				Toast.makeText(UserFinanceAccountActivity.this, "Deposit successfully added!", 
						Toast.LENGTH_SHORT).show();
			} catch (InvalidTransactionCreationException e) {
				String toastMessage = e.getMessage();
				Toast.makeText(UserFinanceAccountActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
			}
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

}
