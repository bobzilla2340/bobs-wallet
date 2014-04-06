package cs2340.bobzilla.bobs_wallet.activites;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.exceptions.InvalidRegistrationException;
import cs2340.bobzilla.bobs_wallet.presenter.RegistrationActivityPresenter;
import cs2340.bobzilla.bobs_wallet.view.RegistrationActivityView;

public class RegistrationActivity extends Activity implements
        RegistrationActivityView {

    private EditText userNameEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmationEditText;
    private EditText emailEditText;
    private RegistrationActivityPresenter registrationActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupActionBar();

        userNameEditText = (EditText) findViewById(R.id.userNameRegistrationEditText);
        firstNameEditText = (EditText) findViewById(R.id.firstNameRegistrationEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameRegistrationEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordRegistrationEditText);
        passwordConfirmationEditText = (EditText) findViewById(R.id.passwordConfirmationRegistrationEditText);
        emailEditText = (EditText) findViewById(R.id.emailRegistrationEditText);

        registrationActivityPresenter = new RegistrationActivityPresenter(this);
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

    @Override
    public String getUserName() {
        return userNameEditText.getText().toString();
    }

    @Override
    public String getFirstName() {
        return firstNameEditText.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastNameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public String getPasswordConfirmation() {
        return passwordConfirmationEditText.getText().toString();
    }

    @Override
    public String getEmailAddress() {
        return emailEditText.getText().toString();
    }

    public void handleUserRegistration(View view) {
        try {
            registrationActivityPresenter.onClick();
            Toast.makeText(RegistrationActivity.this,
                    "Registration is Successful!", Toast.LENGTH_SHORT).show();
        } catch (InvalidRegistrationException e) {
            String toastMessage = e.getMessage();
            Toast.makeText(RegistrationActivity.this, toastMessage,
                    Toast.LENGTH_SHORT).show();
        }
    }

}
