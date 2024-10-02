package build.solution.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


//Data Access Onject
@Dao
interface StudentDAO {
    @Insert
    fun insertStudent(studentTable: StudentTable)

    @Query("Select * from 'StudentTable'")
    fun getAllStudents():List<StudentTable>
}