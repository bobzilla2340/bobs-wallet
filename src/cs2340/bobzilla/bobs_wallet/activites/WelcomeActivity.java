package cs2340.bobzilla.bobs_wallet.activites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class WelcomeActivity extends Activity {
    private Button mSigninButton;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mSigninButton = (Button) findViewById(R.id.loginButtonWelcomeActivity);

        UserList list = UserListSingleton.getInstance().getUserList();
        User admin = new User("admin", "admin", "admin", "pass123",
                "user@verylolz.com");
        list.addUser(admin);

        // // Read in the user list ////
        try {
            Log.e("in on create", "trying to load in object");
            FileInputStream fileIn = this.getApplicationContext()
                    .openFileInput("save.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            UserList serialized = (UserList) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            for (User u : serialized.getUserSet()) {
                if (!u.getFirstName().equals("admin")) {
                    list.addUser(u);
                }
            }
        } catch (Exception e) {
            Log.e("in on create", "getting exception when I load in object");
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
            Log.e("in on pause", "trying to save object");
            fileOut = this.getApplicationContext().openFileOutput("save.ser",
                    Context.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            Log.e("in on pause",
                    "got an exception when trying to save" + e.toString());
        }

    }

}
