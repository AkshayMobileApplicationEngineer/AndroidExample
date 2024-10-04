package build.solution.shayariapp.MOdel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
//Use the class for Recylerview as a Model class / Pojo class
//Use as a Entity Data Class
@Entity("Shayari_table")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id:Int,
    @ColumnInfo("Date")
    var date: Date,
    @ColumnInfo("Shayari")
    var shayari:String,
    )
