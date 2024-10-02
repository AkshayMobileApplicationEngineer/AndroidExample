package demo.solution.problemfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import demo.solution.problemfirebase.utils.EmailUtils;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText email,password;
    private MaterialButton login_button,register_button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Initilaization
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login_button=findViewById(R.id.login_button);
        register_button=findViewById(R.id.register_button);


        /*
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                Log.d("TAG", "Email: "+Email.toString() );
                Log.d("TAG", "Password: "+Password.toString() );

                if(Email.isEmpty() || Password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                }
                else{
                    Login3(Email,Password);
                }
                startActivity(new Intent(getApplication(), MainActivity.class));
            }
        });

         */


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                Log.d("TAG", "Email: " + Email.toString());
                Log.d("TAG", "Password: " + Password.toString());

                if (Email.isEmpty() || Password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                } else {
                    Login3(Email, Password);
                }
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SignUpActivity.class));
            }
        });

    }

    private void Login3(String email, String password) {

        //        Extract the method to a utility class
        if (!EmailUtils.isValidEmail(email)) {
            Toast.makeText(MainActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }


        /* class function
        if (!isValidEmail(email)) {
            Toast.makeText(MainActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }
         */
        if (password.length() < 6) {
            Toast.makeText(MainActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, NextActivity.class)); // Navigate to home screen
                        } else {
                            Toast.makeText(MainActivity.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


/*
 public static boolean isValidEmail(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

 */


}