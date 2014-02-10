package cs2340.bobzilla.bobs_wallet.activites;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.model.User;

public class UserAccountActivity extends Activity {

	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent userAccountIntent = getIntent();
		user = (User)userAccountIntent.getParcelableExtra(LoginActivity.LOGIN_USER_ACCOUNT);
		setUpUserAccountScreen();
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

}
