package edu.tjhsst.eluk.loginscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.AbstractSequentialList;
import java.util.Map;

public class MainActivity extends Activity {
    //Firebase.ResultHandler function;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        final Firebase ref = new Firebase("https://loginscreen99.firebaseio.com/");
        final TextView error1 = (TextView)findViewById(R.id.error1);
        final EditText username = (EditText)findViewById(R.id.usernamebox);
        final EditText password = (EditText)findViewById(R.id.passwordbox);
        Button create = (Button)findViewById(R.id.create);
        Button login = (Button)findViewById(R.id.enter);
        //ref.setAndroidContext(0);

        ref.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                } else {
                    // user is not logged in
                }
            }
        });

        //Firebase ref = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com");
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ref.createUser("" + username.getText(), "" + password.getText(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                error1.setText("Successfully created user account!");
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
                error1.setText("Account could not be created! Try Again!");
            }
        });
            }
        });

       // Firebase ref = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com");

       // Firebase ref = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.authWithPassword("" + username.getText(), "" + password.getText(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        setContentView(R.layout.activity_main2);
                        //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // there was an error
                        error1.setText("Authetication not possible! Try again!");
                    }
                });
            }
            });
// Authenticate users with a custom Firebase token
        //ref.authWithCustomToken("<token>", authResultHandler);

// Alternatively, authenticate users anonymously
       // ref.authAnonymously(authResultHandler);

// Or with an email/password combination
        //ref.authWithPassword("jenny@example.com", "correcthorsebatterystaple", authResultHandler);

// Or via popular OAuth providers ("facebook", "github", "google", or "twitter")
       // ref.authWithOAuthToken("<provider>", "<oauth-token>", authResultHandler);
    }
    //myFirebaseRef.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
      //  @Override
        //public void onSuccess(Map<String, Object> result) {
          //  System.out.println("Successfully created user account with uid: " + result.get("uid"));
        //}
        //@Override
        //public void onError(FirebaseError firebaseError) {
            // there was an error
        //}
    //});
    //Firebase ref2 = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com");
   // ref.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>()
    //private final Object FirebaseError;

    //{
      //  @Override
       // public void onSuccess AbstractSequentialList result;
        //(Map<String, Object> result) {
          //  System.out.println("Successfully created user account with uid: " + result.get("uid"));
       // }

        //@Override
        //public void onError(FirebaseError Object firebaseError;
        //firebaseError) {
            // there was an error
        //}
    //});
    //Firebase ref = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com");
    //ref.authWithPassword("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.AuthResultHandler() {
      //  @Override
       // public void onAuthenticated(AuthData authData) {
         //   System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
       // }

        //@Override
       // public void onAuthenticationError(FirebaseError firebaseError) {
            // there was an error
       // }
    //});

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
