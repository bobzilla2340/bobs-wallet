package cs2340.bobzilla.bobs_wallet.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.model.LoginVerifier;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;

public class LoginActivity extends Activity {

	public static final String LOGIN_USER_NAME = "cs2340.bobzilla.bobs_wallet.LoginActivity.LOGIN_USER_ACCOUNT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void handleUserLogin(View view) {
		EditText userNameEditText = (EditText)findViewById(R.id.userNameLoginEditText);
		EditText passwordEditText = (EditText)findViewById(R.id.passwordLoginEditTet);
		
		String userName = userNameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		
		Log.d("login activity", "length of user list: " + UserListSingleton.getInstance().getUserList().size());
		
		if(userName.equals("")) {
			Toast.makeText(LoginActivity.this, "Please enter a Username!", Toast.LENGTH_SHORT).show();
		}
		else if(password.equals("")) {
			Toast.makeText(LoginActivity.this, "Please enter a Password!", Toast.LENGTH_SHORT).show();
		}
		else if(!(LoginVerifier.veirfyUserPassword(UserListSingleton.getInstance().getUserList(), userName, password) &&
					LoginVerifier.verifyUserName(UserListSingleton.getInstance().getUserList(), userName))) {
			Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(LoginActivity.this, "Welcome User " + userName + "!", Toast.LENGTH_SHORT).show();
			
			Intent userAccountActivityIntent = new Intent(LoginActivity.this, UserAccountActivity.class);
			userAccountActivityIntent.putExtra(LoginActivity.LOGIN_USER_NAME, userName);
			startActivity(userAccountActivityIntent);
		}
	}
}
