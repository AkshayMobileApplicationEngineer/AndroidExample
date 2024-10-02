package build.solution.roomdatabase

import android.content.Context
import androidx.room.Room

object DbHandler {
    fun getDB(context: Context):StudentDB{
        //name of the database
        return  Room.databaseBuilder(context ,StudentDB::class.java,"StudentDB")
            .allowMainThreadQueries()
            .build()
    }
}