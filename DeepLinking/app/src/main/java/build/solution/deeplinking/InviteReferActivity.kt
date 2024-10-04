package build.solution.deeplinking

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class InviteReferActivity : AppCompatActivity() {
    private lateinit var referralLinkLayout: LinearLayout
    private lateinit var generateLinkLayout: LinearLayout

    private lateinit var referralCode: String
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_refer)

        referralLinkLayout = findViewById(R.id.refral_item_layout)
        generateLinkLayout = findViewById(R.id.create_refral_item_layout)

        // Get the data from the intent safely
        val firstName = intent.getStringExtra("FIRST_NAME") ?: ""
        val lastName = intent.getStringExtra("LAST_NAME") ?: ""
        val username = intent.getStringExtra("USERNAME") ?: ""
        val mobileNumber = intent.getStringExtra("MOBILE_NUMBER") ?: ""

        // Generate abbreviations safely
        val firstNameAbbrev = firstName.take(1)
        val lastNameAbbrev = lastName.take(2)
        val usernameAbbrev = username.take(2)
        val mobileNumberAbbrev = mobileNumber.take(2)

        // Construct the referral code
        referralCode = "$firstNameAbbrev$lastNameAbbrev$usernameAbbrev$mobileNumberAbbrev"
        userId = usernameAbbrev

        // Print the generated referral code to the console
        println(referralCode)

        // Listener to generate and show the referral link
        generateLinkLayout.setOnClickListener {
            val referralLink = generateReferralLink(userId, referralCode)
            showReferralLinkDialog(referralLink)
        }

        // Listener to share the referral link
        referralLinkLayout.setOnClickListener {
            shareReferralLink(userId, referralCode)
        }
    }

    private fun generateReferralLink(userId: String, referralCode: String): String {
        return "https://deeplinkingproblem.vercel.app/Referral.html?userid=$userId&referralcode=$referralCode"
    }

    private fun showReferralLinkDialog(referralLink: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Your Referral Link")
            .setMessage(referralLink)
            .setPositiveButton("Share") { _, _ -> shareReferralLink(userId, referralCode) }
            .setNeutralButton("Copy") { _, _ -> copyLinkToClipboard(referralLink) }
            .setNegativeButton("Close") { dialog, _ -> dialog.dismiss() }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun copyLinkToClipboard(link: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Referral Link", link)
        clipboard.setPrimaryClip(clip)

        // Show a toast to inform the user that the link has been copied
        Toast.makeText(this, "Referral link copied to clipboard!", Toast.LENGTH_SHORT).show()
    }

    private fun shareReferralLink(userId: String, referralCode: String) {
        val referralLink = generateReferralLink(userId, referralCode)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Join me using this referral link: $referralLink")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share referral link via"))
    }
}
