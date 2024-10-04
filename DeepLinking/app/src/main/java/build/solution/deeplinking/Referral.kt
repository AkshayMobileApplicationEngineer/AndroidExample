package build.solution.deeplinking

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "referrals")
data class Referral(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: String,
    val referralCode: String
)