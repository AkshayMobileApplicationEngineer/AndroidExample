package build.solution.shayariapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import build.solution.shayariapp.MOdel.DataEntity

@Dao
public interface ShayariDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShayari(dataEntity: DataEntity)


    @Delete
    suspend fun deleteShayari(dataEntity: DataEntity)

    @Update
    suspend fun updateshayari(dataEntity: DataEntity)

    @Query("SELECT * FROM Shayari_table ORDER BY date   DESC")
    fun getAllShayari():List<DataEntity>
}