package build.solution.deeplinking

import android.content.Context
import android.content.SharedPreferences

class ReferralManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("referral_prefs", Context.MODE_PRIVATE)

    fun storeReferralData(userId: String, referralCode: String) {
        val editor = sharedPreferences.edit()
        editor.putString("referral_user_id", userId)
        editor.putString("referral_code", referralCode)
        editor.apply()
    }

    fun getReferralData(): Pair<String?, String?> {
        val userId = sharedPreferences.getString("referral_user_id", null)
        val referralCode = sharedPreferences.getString("referral_code", null)
        return Pair(userId, referralCode)
    }
}
