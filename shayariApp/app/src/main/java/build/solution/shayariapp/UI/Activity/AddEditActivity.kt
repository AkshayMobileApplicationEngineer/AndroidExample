package build.solution.shayariapp.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import build.solution.shayariapp.R

class AddEditActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var toolbarLayout: CollapsingToolbarLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)  // Inflate your layout here

        // Initialize views using findViewById
        toolbar = findViewById(R.id.toolbar)
        toolbarLayout = findViewById(R.id.toolbar_layout)


        // Set up the toolbar
        setSupportActionBar(toolbar)
        toolbarLayout.title = "Your Custom Title"

        // You can set up a click listener for the Floating Action Button

    }
}
