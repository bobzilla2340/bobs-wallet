package cs2340.bobzilla.bobs_wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	Button mSigninButton;
	Button mRegisterButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mSigninButton = (Button)findViewById(R.id.signin);
		mSigninButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(WelcomeActivity.this, "Sign In", Toast.LENGTH_SHORT)
				.show();
				Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
				startActivity(loginIntent);
			}
		});
		
		mRegisterButton = (Button)findViewById(R.id.register);
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
