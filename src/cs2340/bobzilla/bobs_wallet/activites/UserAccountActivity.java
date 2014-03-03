package cs2340.bobzilla.bobs_wallet.activites;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidAccountCreationException;
import cs2340.bobzilla.bobs_wallet.view.UserAccountActivityView;
import cs2340.bobzilla.bobs_wallet.presenter.UserAccountActivityPresenter;


public class UserAccountActivity extends Activity implements UserAccountActivityView {
	
	private TextView welcomeTextView;
	private EditText accountNameEditText;
	private EditText interestRateEditText;
	private String userName;
	private UserAccountActivityPresenter userAccountActivityPresenter;
	
	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	public static final String USER_FINANCE_ACCOUNT_NAME="cs2340.bobzilla.bobs_wallet.activities.UserAccountActivity.UserFinanceAccountName";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account);
		setupActionBar();
		Log.e("CRITICAL", "UPDATE YOUR SD CARD");

		Intent userAccountIntent = getIntent();
		userName = userAccountIntent.getStringExtra(LoginActivity.LOGIN_USER_NAME);
		
		welcomeTextView = (TextView)findViewById(R.id.userAccountWelcomeMessage);
		listView = (ListView)findViewById(R.id.userFinanceAccountList);
		welcomeTextView.append(" " + userName + "!");
		
		arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.user_finance_account_list_text_view);
		listView.setAdapter(arrayAdapter);
		
		userAccountActivityPresenter = new UserAccountActivityPresenter(this);

		for(String account: userAccountActivityPresenter.getFinanceAccountNames(userName)) {
			arrayAdapter.add(account);
		}
		
		listView.setOnItemClickListener(new ListViewClickListener());
	}

	private class ListViewClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int arg0,
				long arg1) {
			TextView clickedText = (TextView)view.findViewById(R.id.userFinanceAccountListTextView);
			String clickedString = clickedText.getText().toString();
			Toast.makeText(UserAccountActivity.this, "You clicked " + clickedString, Toast.LENGTH_SHORT).show();
			Intent userFinanceAccountActivityIntent = new Intent(UserAccountActivity.this, UserFinanceAccountActivity.class);
			userFinanceAccountActivityIntent.putExtra(USER_FINANCE_ACCOUNT_NAME, clickedString);
			startActivity(userFinanceAccountActivityIntent);			
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_account, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View view) {
		LayoutInflater inflater = UserAccountActivity.this.getLayoutInflater();
		final View alertDialogView = inflater.inflate(R.layout.alert_dialog_user_account_finance_account_create, null);
		new AlertDialog.Builder(this)
		.setTitle(R.string.user_account_finance_account_create_dialog_box)
		.setNegativeButton("Quit", null)
		.setPositiveButton("Make Account", new AlertDialogClickListener())
		.setView(alertDialogView)
		.show();
		accountNameEditText = (EditText)alertDialogView.findViewById(R.id.alertDialogUserFinanceAccountNameEditText);
		interestRateEditText = (EditText)alertDialogView.findViewById(R.id.alertDialogUserFinanceAccountIntrestRateEditText);

	}
	
	private class AlertDialogClickListener implements DialogInterface.OnClickListener {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			try {
				userAccountActivityPresenter.onClick();
				arrayAdapter.add(accountNameEditText.getText().toString());
				Toast.makeText(UserAccountActivity.this, "Account successfully created!", Toast.LENGTH_SHORT).show();
			} catch (InvalidAccountCreationException e) {
				String toastMessage = e.getMessage();
				Toast.makeText(UserAccountActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public String getWelcome() {
		return welcomeTextView.toString();
	}
	
	@Override
	public String getUserName() {
		return userName;
	}
	
	@Override
	public String getAccountName() {
		return accountNameEditText.getText().toString();
	}
	
	@Override
	public String getInterestRate() {
		return interestRateEditText.getText().toString();
	}

}
