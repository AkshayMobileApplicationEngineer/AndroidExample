package build.solution.deeplinking

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textHello: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Optional: Enable edge-to-edge support
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        textHello = findViewById(R.id.TextHello)
        textHello.setOnClickListener {
            startActivity(Intent(this, InviteReferActivity::class.java))
        }
    }
}
