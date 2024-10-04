package build.solution.shayariapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import build.solution.shayariapp.MOdel.DataEntity

@Database(entities = [DataEntity::class],
    version = 1,
    exportSchema = true
    )
abstract class ShayariDataBase: RoomDatabase() {
    abstract fun getShayariDao():ShayariDao
    private  var INSTANCE :ShayariDataBase?=null

    fun getaDatabase(context: Context):ShayariDataBase{
        if(INSTANCE!=null){
            //handle the data
            Synchronized(this){
                INSTANCE= Room.databaseBuilder(context.applicationContext,ShayariDataBase::class.java,"ShayariDatabase").build()
            }
        }
        return INSTANCE
    }

}