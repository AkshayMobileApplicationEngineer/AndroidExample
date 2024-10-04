package build.solution.deeplinking

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Referral::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun referralDao(): ReferralDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDataBase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
