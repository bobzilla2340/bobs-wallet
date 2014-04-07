package cs2340.bobzilla.bobs_wallet.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidLoginException;
import cs2340.bobzilla.bobs_wallet.presenter.LoginActivityPresenter;
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
    /**
     * This is a reference to the presenter that is tied into this
     * activity.
     */
    private LoginActivityPresenter loginActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = (EditText) findViewById(R.id.userNameLoginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordLoginEditText);

        loginActivityPresenter = new LoginActivityPresenter(this);
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
        try {
            loginActivityPresenter.onClick();
            String userName = getUserName();
            Intent userAccountActivityIntent = new Intent(LoginActivity.this,
                    UserAccountActivity.class);
            userAccountActivityIntent.putExtra(LoginActivity.LOGIN_USER_NAME,
                    userName);
            startActivity(userAccountActivityIntent);
        } catch (InvalidLoginException e) {
            Toast.makeText(LoginActivity.this,
                    "Please enter a valid Username or Password!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
