package cs2340.bobzilla.bobs_wallet;

import android.app.Activity;
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
				
			}
		});
		
		mRegisterButton = (Button)findViewById(R.id.register);
		mRegisterButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(WelcomeActivity.this, "Register", Toast.LENGTH_SHORT);
				
			}
		});
	}
}
