package cs2340.bobzilla.bobs_wallet.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.Parse;

import cs2340.bobzilla.bobs_wallet.R;

/**
 * This is the first screen that the user encounters when
 * they start up the application.
 * @author sai
 *
 */
public class WelcomeActivity extends Activity {
    /**
     * This button takes the user to the login screen.
     */
    private Button mSigninButton;
    /**
     * This button takes the user to the registration screen.
     */
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mSigninButton = (Button) findViewById(R.id.loginButtonWelcomeActivity);

        mSigninButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "Sign In",
                        Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        mRegisterButton = (Button) findViewById(R.id.registerButtonWelcomeActivity);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "Register",
                        Toast.LENGTH_SHORT).show();
                Intent registerIntent = new Intent(WelcomeActivity.this,
                        RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });
        
        // Set application id and client key.
        // Had to wrap it as an AsyncTask, because re-initializing Parse
        // causes a NetworkOnMainThreadException - hopefully will be fixed by Parse
        // in the future.
        new initializeParseTask().execute(this);
        
    }
    private class initializeParseTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... params) {
            Parse.initialize(params[0], "zFAu7f8ISe6ckU2AYWtAfT59NwQe0UWeUOEfmMFs", "ZWhvBgdeRKmUe5wugS8HfmjDXYXTNNSTnULwf6hA");
            return null;
        }

    }

}
