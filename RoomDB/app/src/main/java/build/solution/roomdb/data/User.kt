package build.solution.roomdb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id:Int ,
    val firsrName:String,
    val lastName:String,
    val age:Int
)