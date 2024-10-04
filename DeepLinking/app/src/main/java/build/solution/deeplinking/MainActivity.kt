package build.solution.deeplinking


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var firstInput: TextInputEditText
    private lateinit var secondInput: TextInputEditText
    private lateinit var usernameInput: TextInputEditText
    private lateinit var mobileInput: TextInputEditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        firstInput = findViewById(R.id.firstInput)
        secondInput = findViewById(R.id.secondInput)
        usernameInput = findViewById(R.id.usernameInput)
        mobileInput = findViewById(R.id.mobileInput)
        btnLogin = findViewById(R.id.btnLogin)

        // Set button click listener
        btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val firstName = firstInput.text.toString()
        val lastName = secondInput.text.toString()
        val username = usernameInput.text.toString()
        val mobileNumber = mobileInput.text.toString()

        // Validate inputs (optional)
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || mobileNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Process the input data as needed
        // For example, display it in a Toast
        val message = "First Name: $firstName\nLast Name: $lastName\nUsername: $username\nMobile: $mobileNumber"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // You can also perform further actions like saving to a database, etc.

        val intent = Intent(this, InviteReferActivity::class.java).apply {
            putExtra("FIRST_NAME", firstName)
            putExtra("LAST_NAME", lastName)
            putExtra("USERNAME", username)
            putExtra("MOBILE_NUMBER", mobileNumber)
        }
        startActivity(intent)
    }
}
