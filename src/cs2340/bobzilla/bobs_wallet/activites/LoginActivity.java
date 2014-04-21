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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.view.LoginActivityView;

/**
 * This activity is presented when the user wants to 
 * login to their account.
 * @author sai
 *
 */
public class LoginActivity extends Activity implements LoginActivityView {

    /**
     * This is used to get a reference to the activity
     * when an intent has been initiated.
     */
    public static final String LOGIN_USER_NAME = "cs2340.bobzilla.bobs_wallet.LoginActivity.LOGIN_USER_ACCOUNT";
    /**
     * This is a reference to the field where the users
     * enter in their user name.
     */
    private EditText userNameEditText;
    /**
     * This is a reference to the field where the users
     * enter in their password.
     */
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = (EditText) findViewById(R.id.userNameLoginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordLoginEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getUserName() {
        return userNameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    /**
     * This method takes care of login when the
     * login button is pushed.
     * @param view
     *          The current activity's view this
     *          method interacts with.
     */
    public void handleUserLogin(View view) {
        final String userName = getUserName();
        final String password = getPassword();
        
        // Login in the background
        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    
                    // Start the Account Activity if login is successful
                    startAccountActivity(userName);

                }
                else {
                    // TODO: handle stable current user
                    handleUserLoginError(e);
                }
            }
        });
    }
    
    /**
     * Shows the user their accounts
     * Called when user successfully logs in.
     * @param userName
     */
    private void startAccountActivity(String userName) {
     // Start the Account Activity
        Intent userAccountActivityIntent = new Intent(LoginActivity.this,
                UserAccountActivity.class);
        userAccountActivityIntent.putExtra(LoginActivity.LOGIN_USER_NAME,
                userName);
        startActivity(userAccountActivityIntent);
    }
    
    /**
     * Gives the user feedback on why the login failed.
     * @param e ParseException returned by failed login
     */
    private void handleUserLoginError(ParseException e) {
     // TODO: handle stable current user
        switch(e.getCode()) {
        case ParseException.USERNAME_MISSING:
        case ParseException.PASSWORD_MISSING:
        case ParseException.OBJECT_NOT_FOUND:
            Toast.makeText(LoginActivity.this, "Please enter a valid Username or Password!", Toast.LENGTH_SHORT).show();
            break;
        case ParseException.CONNECTION_FAILED:
            Toast.makeText(getApplicationContext(), "Please connect to the internet.", Toast.LENGTH_SHORT).show();
        }
    }
}
