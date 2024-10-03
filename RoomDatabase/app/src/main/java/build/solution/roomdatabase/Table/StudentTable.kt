package build.solution.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity //table name like id , name ,number ,address etc..............
data class StudentTable (
    val name:String,
    val email:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int
)