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
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;

public class UserAccountActivity extends Activity {

	// before we even get started, make sure this class
	// implements the UserAccountAcitivityView interface!!!
	// be sure to implement the appropriate methods... from
	// the interface... I leave this for you to ponder.
	
	// Allright, we need to make this into MVP
	// First, get all the UI elements here.
	// Get rid of user. you should not be directly
	// interacting with a model element. Instead,
	// modify the presenter code to get the list of
	// already made FinanceAccount objects.
	
	// MAKE THE USERACCOUNTACTIVITYPRESENTER AN INSTANCE FIELD!!!
	
	private User user;
	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	public static final String USER_FINANCE_ACCOUNT_NAME="cs2340.bobzilla.bobs_wallet.activities.UserAccountActivity.UserFinanceAccountName";
	
	
	//in here, you can just set the UI elements to the proper
	//reference. Also, make a private inner class for the
	// OnItemClickListener - this makes the code SO MUCH CLEANER!!
	// in here, you should also initialize the presenter.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account);
		// Show the Up button in the action bar.
		setupActionBar();
		Log.e("CRITICAL", "UPDATE YOUR SD CARD");
		
		Intent userAccountIntent = getIntent();
		String userName = userAccountIntent.getStringExtra(LoginActivity.LOGIN_USER_NAME);
		user = UserListSingleton.getInstance().getUserList().getUser(userName);		
		
		listView = (ListView)findViewById(R.id.userFinanceAccountList);
		arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.user_finance_account_list_text_view);
		listView.setAdapter(arrayAdapter);
		
		setUpUserAccountScreen();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int arg0, long arg1) {
				TextView clickedText = (TextView)view.findViewById(R.id.userFinanceAccountListTextView);
				String clickedString = clickedText.getText().toString();
				Toast.makeText(UserAccountActivity.this, "You clicked " + clickedString, Toast.LENGTH_SHORT).show();
				Intent userFinanceAccountActivityIntent = new Intent(UserAccountActivity.this, UserFinanceAccountActivity.class);
				userFinanceAccountActivityIntent.putExtra(USER_FINANCE_ACCOUNT_NAME, clickedString);
				startActivity(userFinanceAccountActivityIntent);
			}
		});
	}
	
	private void setUpUserAccountScreen() {
		
		TextView welcome = (TextView)findViewById(R.id.userAccountWelcomeMessage);
		TextView userName = (TextView)findViewById(R.id.userAccountUserNamePrompt);
		TextView firstName = (TextView)findViewById(R.id.userAccountFirstNamePrompt);
		TextView lastName = (TextView)findViewById(R.id.userAccountLastNamePrompt);
		TextView email = (TextView)findViewById(R.id.userAccountEMailPrompt);
		
		welcome.append(" " + user.getFirstName() + "!");
		userName.append(" " + user.getUserName());
		firstName.append(" " + user.getFirstName());
		lastName.append(" " + user.getLastName());
		email.append(" " + user.getEmail());
		
		
		
		for(String account: user.getFinanceAccountList().keySet()) {
			arrayAdapter.add(account);
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
	
	// pls completely refactor this method... you
	// should make a private innner class that
	// implements the DialogInterface.OnClickListener()
	// interface.
	public void onClick(View view) {
		
		LayoutInflater inflater = UserAccountActivity.this.getLayoutInflater();
		final View alertDialogView = inflater.inflate(R.layout.alert_dialog_user_account_finance_account_create, null);
		new AlertDialog.Builder(this)
		.setTitle(R.string.user_account_finance_account_create_dialog_box)
		.setNegativeButton("Quit", null)
		.setPositiveButton("Make Account", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				EditText accountName = (EditText)alertDialogView.findViewById(R.id.alertDialogUserFinanceAccountNameEditText);
				EditText interestRate = (EditText)alertDialogView.findViewById(R.id.alertDialogUserFinanceAccountIntrestRateEditText);
				Log.d("debugging message", "edit text: " + accountName);
				String account = accountName.getText().toString();
				double interest;
				if(account.equals("")) {
					Toast.makeText(UserAccountActivity.this, "Please enter a valid account name!", Toast.LENGTH_SHORT).show();
				}
				else if(interestRate.getText().toString().equals("")) {
					Toast.makeText(UserAccountActivity.this, "Please enter a valid interest rate!", Toast.LENGTH_SHORT);
				}
				else if(Double.parseDouble(interestRate.getText().toString()) <= 0) {
					Toast.makeText(UserAccountActivity.this, "Please enter a valid interest rate!", Toast.LENGTH_SHORT);
				}
				else {
					interest = Double.parseDouble(interestRate.getText().toString());
					user.addFinanceAccount(account, interest);
					Toast.makeText(UserAccountActivity.this, "Account successfully created!", Toast.LENGTH_SHORT).show();
					arrayAdapter.add(account);
				}
			}
		})
		.setView(alertDialogView)
		.show();
		
		
		
		
	}
	
	// I would like to use this private inner class instead of the anonymous class.
	// Make it so!
	// Ideally, you should be making a call to the presenter here.
	private class AlertDialogClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
