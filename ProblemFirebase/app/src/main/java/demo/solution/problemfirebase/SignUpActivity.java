package demo.solution.problemfirebase;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import demo.solution.problemfirebase.Model.User;
import demo.solution.problemfirebase.utils.EmailUtils;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText name, mobile, username, email, password;
    private MaterialButton register_button, login_button;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

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
                    register(Email, Password, Name, Mobile, Username);
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

    private void register(String email, String password, String name, String mobile, String username) {
        if (!EmailUtils.isValidEmail(email)) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUserToDatabase(name, mobile, email, username,password);
                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    /*
    private void saveUserToDatabase(String name, String mobile, String email, String username,String password) {
        databaseReference = database.getReference("name").child(name);
        databaseReference = database.getReference("mobile").child(mobile);
        databaseReference = database.getReference("email").child(email);
        databaseReference = database.getReference("username").child(username);
        databaseReference = database.getReference("password").child(password);

        User user = new User(name, mobile, email, username,password);

        databaseReference.setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                try {
                    // Create JSON object
                    JSONObject userData = new JSONObject();
                    userData.put("name", name);
                    userData.put("mobile", mobile);
                    userData.put("email", email);
                    userData.put("username", username);
                    userData.put("password", password); // Avoid storing password if possible

                    // Convert to JSON string
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(userData);

                    // Log JSON string
                    Log.d("UserData", jsonString);

                    // Show JSON string in a dialog
                    showJsonDialog(this, jsonString);
                } catch (JSONException e) {
                    Log.e("JSONError", "Failed to create JSON object", e);
                }
            } else {
                Log.e("DBError", "Failed to save user data", task.getException());
            }
        });
    }
    */

    /*
    private void saveUserToDatabase(String name, String mobile, String email, String username, String password) {
        // Use a single reference under "users" with username or a unique ID as the key

        String sanitizedUsername = username.replace(".", "_").replace("@", "_");
        String sanitizedEmail = username.replace(".", "_").replace("@", "_");
        databaseReference = database.getReference("users").child(sanitizedUsername);
        databaseReference = database.getReference("name").child(name);
        databaseReference = database.getReference("mobile").child(mobile);
        databaseReference = database.getReference("email").child(sanitizedEmail);
        databaseReference = database.getReference("password").child(password);


         // Ensure username is safe

        User user = new User(name, mobile, email, username, password);

        // Save user data
        databaseReference.setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                try {
                    // Create JSON object
                    JSONObject userData = new JSONObject();
                    userData.put("name", name);
                    userData.put("mobile", mobile);
                    userData.put("email", email);
                    userData.put("username", username);
                    userData.put("password", password); // Avoid storing password if possible

                    // Convert to JSON string
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(userData);

                    // Log JSON string
                    Log.e("UserData", jsonString);

                    // Show JSON string in a dialog
                    showJsonDialog(this, jsonString);
                } catch (JSONException e) {
                    Log.e("JSONError", "Failed to create JSON object", e);
                }
            } else {
                Log.e("DBError", "Failed to save user data", task.getException());
            }
        });
    }

     */

    private void saveUserToDatabase(String name, String mobile, String email, String username, String password) {
        // Sanitize the username (replace "." and "@" with underscores to avoid conflicts)
        String sanitizedUsername = username.replace(".", "_").replace("@", "_");

        // Get a reference to the "users" node and store the data under the sanitized username
        DatabaseReference userReference = database.getReference("users").child(sanitizedUsername);

        // Create a map or a User object with the user data
        Map<String, String> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("mobile", mobile);
        userData.put("email", email);
        userData.put("password", password);  // Avoid storing password if possible

        // Save the user data under the sanitized username in the database
        userReference.setValue(userData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                try {
                    // Create a JSON object with user data
                    JSONObject userDataJson = new JSONObject();
                    userDataJson.put("name", name);
                    userDataJson.put("mobile", mobile);
                    userDataJson.put("email", email);
                    userDataJson.put("username", sanitizedUsername);
                    userDataJson.put("password", password); // Avoid storing password if possible

                    // Convert to JSON string using Gson
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(userDataJson);

                    // Log the JSON string
                    Log.e("UserData", jsonString);

                    // Show the JSON string in a dialog
                    //showJsonDialog(this, jsonString);
                } catch (JSONException e) {
                    Log.e("JSONError", "Failed to create JSON object", e);
                }
            } else {
                Log.e("DBError", "Failed to save user data", task.getException());
            }
        });
    }




    private boolean validateFields(String email, String password, String name, String mobile, String username) {
        if (name.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "validateFields: "+"Please enter name");
            return false;
        }
        if (mobile.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "validateFields: "+"Please enter mobile number");
            return false;
        }
        if (username.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "validateFields: "+"Please enter username");
            return false;
        }
        if (email.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "validateFields: "+"Please enter email");
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "validateFields: "+"Please enter password");
            return false;
        }
        return true;
    }
    /*
    private void showJsonDialog(Context context, String jsonString) {
        new AlertDialog.Builder(context)
                .setTitle("User Data JSON")
                .setMessage(jsonString)
                .setPositiveButton("OK", null)
                .show();
    }
     */
}
