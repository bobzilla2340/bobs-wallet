package cs2340.bobzilla.bobs_wallet.activites;

import java.util.Date;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
import cs2340.bobzilla.bobs_wallet.activites.DatePickerFragment.OnDateChangeListener;
import cs2340.bobzilla.bobs_wallet.activites.ReportActivity.ReportType;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidAccountCreationException;
import cs2340.bobzilla.bobs_wallet.presenter.UserAccountActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.UserAccountActivityView;


public class UserAccountActivity extends FragmentActivity implements UserAccountActivityView, OnDateChangeListener {
	
	private TextView welcomeTextView;
	private EditText accountNameEditText;
	private EditText interestRateEditText;
	private String userName;
	private UserAccountActivityPresenter userAccountActivityPresenter;
	
	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	public static final String USER_FINANCE_ACCOUNT_NAME="cs2340.bobzilla.bobs_wallet.activities.UserAccountActivity.UserFinanceAccountName";
	public static final String USER_NAME="cs2340.bobzilla.bobs_wallet.activities.UserAccountActivity.UserName";
	
	private static final String DIALOG_DATE = "date";
	public static final String EXTRA_DATETYPE = "dateType";
	private ReportType mReportType;
	private Date mReportStartDate;
	private Date mReportEndDate;

	
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
			Toast.makeText(UserAccountActivity.this, "Viewing " + clickedString + "...", Toast.LENGTH_SHORT).show();
			Intent userFinanceAccountActivityIntent = new Intent(UserAccountActivity.this, UserFinanceAccountActivity.class);
			userFinanceAccountActivityIntent.putExtra(USER_FINANCE_ACCOUNT_NAME, clickedString);
			userFinanceAccountActivityIntent.putExtra(USER_NAME, userName);
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
		case R.id.create_report:
			Toast.makeText(UserAccountActivity.this, "Clicked on create report.", Toast.LENGTH_SHORT).show();
			
			/*
			 * Dialog to select report type
			 */
			AlertDialog.Builder reportTypeBuilder = new AlertDialog.Builder(UserAccountActivity.this);
			// Add list options
			reportTypeBuilder.setTitle(R.string.report_type_prompt)
				.setItems(R.array.report_types, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) mReportType = ReportType.SPENDINGCATEGORY;
						FragmentManager fm = UserAccountActivity.this.getSupportFragmentManager();
						DatePickerFragment startDateDialog = new DatePickerFragment();
						
						Bundle args = new Bundle();
						args.putString(EXTRA_DATETYPE, "start");
						startDateDialog.setArguments(args);
						startDateDialog.show(fm, DIALOG_DATE);
					}
				})
				// Add cancel button
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(UserAccountActivity.this, "User exited dialog.", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				})
				.create().show();
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View view) {
		LayoutInflater inflater = UserAccountActivity.this.getLayoutInflater();
		final View alertDialogView = inflater.inflate(R.layout.alert_dialog_user_account_finance_account_create, null);
		new AlertDialog.Builder(this)
		.setTitle(R.string.user_account_finance_account_create_dialog_box)
		.setNegativeButton("Cancel", null)
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
	
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void onDateChange(Date date, String dateType) {
		if (dateType.equals("start")) {
			mReportStartDate = date;
			Log.e("UserAccountActivity", "startDate: " + mReportStartDate.toString());
			FragmentManager fm = UserAccountActivity.this.getSupportFragmentManager();
			DatePickerFragment endDateDialog = new DatePickerFragment();
			
			Bundle args = new Bundle();
			args.putString(EXTRA_DATETYPE, "end");
			endDateDialog.setArguments(args);
			endDateDialog.show(fm, DIALOG_DATE);
		}
		else {
			mReportEndDate = date;
			Log.e("UserAccountActivity", "endDate: " + mReportEndDate.toString());
			
			Intent reportActivityIntent = new Intent(UserAccountActivity.this, ReportActivity.class);
			reportActivityIntent.putExtra(ReportActivity.EXTRA_TYPE, mReportType);
			reportActivityIntent.putExtra(ReportActivity.EXTRA_USERNAME, userName);
			reportActivityIntent.putExtra(ReportActivity.EXTRA_STARTDATE, mReportStartDate);
			reportActivityIntent.putExtra(ReportActivity.EXTRA_ENDDATE, mReportEndDate);
			startActivity(reportActivityIntent);
		}
	}

}
