package build.solution.deeplinking

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeepLinkActivity : AppCompatActivity() {
    private lateinit var urlText: TextView
    private lateinit var referralManager: ReferralManager
    private lateinit var database: AppDatabase

    private val TAG = "DeepLinkActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)

        urlText = findViewById(R.id.UrlTextView)
        referralManager = ReferralManager(this)
        database = AppDatabase.getDatabase(this)

        // Handle the incoming intent
        handleDeepLink()
    }

    private fun handleDeepLink() {
        intent?.data?.let { uri ->
            val userId = uri.getQueryParameter("userid")
            val referralCode = uri.getQueryParameter("referralcode")
            val hostName = uri.host
            val path = uri.path

            if (userId != null && referralCode != null) {
                // Store referral data in the local database
                storeReferralData(userId, referralCode)

                // Log the information
                Log.e(TAG, "User ID: $userId, Referral Code: $referralCode, Host: $hostName, Path: $path")

                // Set the text in the TextView with hostname and path
                urlText.text = "User joined through UserID: $userId\n" +
                        "Referral Code: $referralCode\n" +
                        "Host: $hostName\n" +
                        "Path: $path"

                // Show a Toast message

                Toast.makeText(this, "User ID: $userId, Referral Code: $referralCode\nHost: $hostName\nPath: $path", Toast.LENGTH_LONG).show()
                // Log the information in JSON format





























































            } else {
                // Handle the case where parameters are missing
                Toast.makeText(this, "Invalid referral link", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun storeReferralData(userId: String, referralCode: String) {
        val referral = Referral(userId = userId, referralCode = referralCode)
        CoroutineScope(Dispatchers.IO).launch {
            database.referralDao().insert(referral)
            Log.d(TAG, "Referral data stored: $referral")
        }
    }
}
