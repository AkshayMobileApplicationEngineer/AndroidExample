package build.solution.shayariapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import build.solution.shayariapp.Adapter.RvAdapter
import build.solution.shayariapp.MOdel.DataEntity
import build.solution.shayariapp.UI.Activity.AddEditActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var flatingmsg: FloatingActionButton
    private lateinit var recyclerViewMain: RecyclerView
    private lateinit var shayariList: ArrayList<DataEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Floating Action Button and RecyclerView
        flatingmsg = findViewById(R.id.floatingMsg)
        recyclerViewMain = findViewById(R.id.RecyclerViewMain)

        // Initialize shayariList
        shayariList = ArrayList()

        // Set up UI components
        viewModelUI()
    }

    private fun viewModelUI() {
        // Set click listener for the Floating Action Button
        flatingmsg.setOnClickListener {
            Toast.makeText(this, "Floating Action Bar", Toast.LENGTH_LONG).show()
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }

        // Populate the shayariList with data
        shayariList.addAll(SimpleData.date)

        // Set up the RecyclerView
        recyclerViewMain.layoutManager = LinearLayoutManager(this)
        recyclerViewMain.adapter = RvAdapter(this, shayariList) // Assuming RvAdapter is your adapter class
    }
}
