package build.solution.deeplinking

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InviteReferActivity : AppCompatActivity() {
    lateinit var RefrealLink :LinearLayout
    lateinit var GernertatLink:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_invite_refer)
        RefrealLink= findViewById<LinearLayout>(R.id.refral_item_layout)
        GernertatLink= findViewById<LinearLayout>(R.id.create_refral_item_layout)




        

        GernertatLink.setOnClickListener {
            val userId = "12345"
            val referralCode = "ABCDEF"
            val referralLink = generateReferralLink(userId, referralCode)
            println("Generated Referral Link: $referralLink")

        }

        RefrealLink.setOnClickListener {
            shareReferralLink("12345", "ABCDEF")
        }

    }

    fun generateReferralLink(userId: String, referralCode: String): String {
        //return "https://yourapp.com/referral?userid=$userId&referralcode=$referralCode"
       // return "https://deeplinkingproblem.vercel.app/Referral.html?userid=$userId&referralcode=$referralCode"
        return "https://deeplinkingproblem.vercel.app/referral?userid=$userId&referralcode=$referralCode"
    }
    fun shareReferralLink(userId: String, referralCode: String) {
        val referralLink = generateReferralLink(userId, referralCode)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Join me using this referral link: $referralLink")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share referral link via"))
    }



}