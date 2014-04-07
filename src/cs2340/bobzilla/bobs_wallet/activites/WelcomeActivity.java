package cs2340.bobzilla.bobs_wallet.activites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import cs2340.bobzilla.bobs_wallet.R;
import cs2340.bobzilla.bobs_wallet.model.User;
import cs2340.bobzilla.bobs_wallet.model.UserList;
import cs2340.bobzilla.bobs_wallet.model.UserListSingleton;

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
    /**
     * This is the admin string.
     */
    private String adminString = "admin";
    /**
     * This is the log message type.
     */
    private String onCreateMessage = "in on create";
    /**
     * This is the on pause message.
     */
    private String onPauseMessage = "in on pause";
    /**
     * The save file of the appliaction.
     */
    private String saveFile = "save.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mSigninButton = (Button) findViewById(R.id.loginButtonWelcomeActivity);

        UserList list = UserListSingleton.getInstance().getUserList();
        User admin = new User(adminString, adminString, adminString, "pass123",
                "user@verylolz.com");
        list.addUser(admin);

        // // Read in the user list ////
        try {
            Log.e(onCreateMessage, "trying to load in object");
            FileInputStream fileIn = this.getApplicationContext()
                    .openFileInput(saveFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            UserList serialized = (UserList) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            for (User u : serialized.getUserSet()) {
                if (!u.getFirstName().equals(adminString)) {
                    list.addUser(u);
                }
            }
        } catch (FileNotFoundException e) {
            Log.e(onCreateMessage, "getting exception when I load in object");
        } catch (IOException e) {
            Log.e(onCreateMessage, "got an error performing IO");
        } catch (ClassNotFoundException e) {
            Log.e(onCreateMessage, "got an error creating the class");
        }

        mSigninButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "Sign In",
                        Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                // loginIntent.putExtra(USER_LIST_MESSAGE, userList);
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
    }

    @Override
    protected void onPause() {
        // // Save the user list ////
        super.onDestroy();
        UserList list = UserListSingleton.getInstance().getUserList();

        FileOutputStream fileOut;
        ObjectOutputStream objectOut;

        try {
            Log.e(onPauseMessage, "trying to save object");
            fileOut = this.getApplicationContext().openFileOutput(saveFile,
                    Context.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            Log.e(onPauseMessage,
                    "got an exception when trying to save" + e.toString());
        } catch (IOException e) {
            Log.e(onPauseMessage, "got an exception with IO");
        }

    }

}
