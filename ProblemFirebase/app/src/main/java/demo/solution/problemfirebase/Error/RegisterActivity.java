package demo.solution.problemfirebase.Error;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

import demo.solution.problemfirebase.MainActivity;
import demo.solution.problemfirebase.Model.User;
import demo.solution.problemfirebase.R;
import demo.solution.problemfirebase.utils.EmailUtils;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText name;
    private TextInputEditText mobile;
    private TextInputEditText username;
    private TextInputEditText email;
    private TextInputEditText password;
    private MaterialButton register_button, login_button;
    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private int timerinSecond = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        name = findViewById(R.id.Name);
        mobile = findViewById(R.id.mobile);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register_button = findViewById(R.id.register_button);
        login_button = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Mobile = mobile.getText().toString();
                String Email = email.getText().toString();
                String Username = username.getText().toString();
                String Password = password.getText().toString();

                if (validateFields(Email, Password, Name, Mobile, Username)) {
                    runTimeDataBase(database, Name, Mobile, Email, Username, Password);
                    register(Email, Password);
                    Log.d("TAG", "Name: " + Name.toString());
                    Log.d("TAG", "Mobile: " + Mobile.toString());
                    Log.d("TAG", "Email: " + Email.toString());
                    Log.d("TAG", "Username: " + Username.toString());
                    Log.d("TAG", "Password: " + Password.toString());
                }


            }


        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }


    private void register(String email, String password) {
        // Email and password validation

//        Extract the method to a utility class
        if (!EmailUtils.isValidEmail(email)) {
            Toast.makeText(RegisterActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }



        /* Using function with another class
        if (!MainActivity.isValidEmail(email)) {
            Toast.makeText(RegisterActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }
        */



/*    how to use function in another class using of creating object
        MainActivity mainActivity = new MainActivity();
        if (!mainActivity.isValidEmail(email)) {
            Toast.makeText(RegisterActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        */

        if (password.length() < 6) {
            Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }, timerinSecond);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    // Helper method to validate email format
    public static boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateFields(String email, String password, String name, String mobile, String username) {
        if (email.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mobile.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (username.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    /*
     SignInCredential googleCredential = oneTapClient.getSignInCredentialFromIntent(data);
    String idToken = googleCredential.getGoogleIdToken();
        if (idToken !=  null) {
        // Got an ID token from Google. Use it to authenticate
        // with Firebase.
        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }
    * */



    private void runTimeDataBase(FirebaseDatabase database, String name, String mobile, String email, String username, String password) {
        databaseReference = database.getReference("users").child(username);
        User user = new User(name, mobile, email, username,password);
        databaseReference.setValue(user);
    }

}


