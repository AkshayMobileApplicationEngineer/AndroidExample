package build.solution.deeplinking

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DeepLinkActivity : AppCompatActivity() {

    private lateinit var urlTextView: TextView
    private var urlDeeplink: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Optional: Enable edge-to-edge support
        enableEdgeToEdge()

        setContentView(R.layout.activity_deep_link)
        urlTextView = findViewById(R.id.UrlTextView)

        // Get the data from the intent
        urlDeeplink = intent.data

        // Check if the URL is not null
        urlDeeplink?.let {
            // Display the host and path
            val host = it.host ?: "No Host"
            val path = it.path ?: "No Path"
            urlTextView.text = "Host: $host\nPath: $path"
        } ?: run {
            urlTextView.text = "No Deep Link Data"
        }
    }
}
