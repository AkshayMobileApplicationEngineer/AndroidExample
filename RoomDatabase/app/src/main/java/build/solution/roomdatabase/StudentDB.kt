package build.solution.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [StudentTable::class])

abstract class StudentDB : RoomDatabase() {
    abstract val studentDao:StudentDAO
}