package cs2340.bobzilla.bobs_wallet.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.User;

public class WelcomeActivity extends Activity {
	private Button mSigninButton;
	private Button mRegisterButton;
	public final static String USER_LIST_MESSAGE = "cs2340.bobzilla.bobs_wallet.activites.LoginActivity.USER_LIST_MESSAGE";
	private UserList userList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mSigninButton = (Button)findViewById(R.id.loginButtonWelcomeActivity);
		
		userList = new UserList();
		userList.addUser(new User("admin", "admin", "admin", "pass123", "user@verylolz.com"));
		
		mSigninButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Toast.makeText(WelcomeActivity.this, "Sign In", Toast.LENGTH_SHORT)
				.show();
				Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
				loginIntent.putExtra(USER_LIST_MESSAGE, userList);
				startActivity(loginIntent);
			}
		});
		
		mRegisterButton = (Button)findViewById(R.id.registerButtonWelcomeActivity);
		mRegisterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(WelcomeActivity.this, "Register", Toast.LENGTH_SHORT)
				.show();
				Intent registerIntent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
				startActivity(registerIntent);
			}
		});
	}
}