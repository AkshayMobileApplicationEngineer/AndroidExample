package build.solution.deeplinking

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReferralDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(referral: Referral)

    @Query("SELECT * FROM referrals")
     fun getAllReferrals(): List<Referral>
}
