package cs2340.bobzilla.bobs_wallet.activites;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.model.RegistrationVerifier;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;

public class RegistrationActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		//Intent registerIntent = getIntent();
		//userList = (UserList)registerIntent.getParcelableExtra(WelcomeActivity.USER_LIST_MESSAGE);
		
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.registration, menu);
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
	
	public void handleUserRegistration(View view) {
		
		EditText userNameEditText = (EditText)findViewById(R.id.userNameRegistrationEditText);
		EditText firstNameEditText = (EditText)findViewById(R.id.firstNameRegistrationEditText);
		EditText lastNameEditText = (EditText)findViewById(R.id.lastNameRegistrationEditText);
		EditText passwordEditText = (EditText)findViewById(R.id.passwordRegistrationEditText);
		EditText passwordConfirmationEditText = (EditText)findViewById(R.id.passwordConfirmationRegistrationEditText);
		EditText emailEditText = (EditText)findViewById(R.id.emailRegistrationEditText);
		
		String userName = userNameEditText.getText().toString();
		String firstName = firstNameEditText.getText().toString();
		String lastName = lastNameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		String confirmation = passwordConfirmationEditText.getText().toString();
		String email = emailEditText.getText().toString();
		
		UserList userList = UserListSingleton.getInstance().getUserList();
		
		if(RegistrationVerifier.isUserNameTaken(userList, userName)) {
			Toast.makeText(this, "That username is already taken!", Toast.LENGTH_SHORT).show();
		}
		else if(!RegistrationVerifier.checkPasswordMatch(password, confirmation)) {
			Toast.makeText(this, "Please enter a valid password!", Toast.LENGTH_SHORT).show();
		}
		
		else if(!RegistrationVerifier.verifyEmail(email)) {
			Toast.makeText(this, "Please enter a valid email address!", Toast.LENGTH_SHORT).show();
		}
		else {
			User newUser = new User(userName, firstName, lastName, password, email);
			Log.d("registration screen", "added new user");
			UserListSingleton.getInstance().getUserList().addUser(newUser);
		}
	}

}
