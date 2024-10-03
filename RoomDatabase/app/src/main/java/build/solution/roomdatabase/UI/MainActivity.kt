package build.solution.roomdatabase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var btnInsert: Button
    private lateinit var db: StudentDB // Use StudentDB instead of DbHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialization
        btnInsert = findViewById<Button>(R.id.botton_insert) // Ensure this ID matches your layout
        db = DbHandler.getDB(this) // This should return a StudentDB instance

        btnInsert.setOnClickListener {
            deleteStudent(1) // Pass the student ID you want to delete
        }
    }

    private fun deleteStudent(studentId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            db.studentDao.deleteStudent(studentId)
            // Optionally update UI on the main thread after deletion
            withContext(Dispatchers.Main) {
                Log.d("MainActivity", "Student with ID $studentId deleted.")
            }
        }
    }
}
