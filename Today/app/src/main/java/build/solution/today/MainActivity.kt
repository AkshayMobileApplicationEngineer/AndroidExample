package build.solution.today

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import build.solution.retrofit.R
import build.solution.today.rewards.ApiService
import build.solution.today.rewards.RewardsItem

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RewardsAdapter
    private var rewardsList: ArrayList<RewardsItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get rewards data
        getRewar()
    }

    private fun getRewar() {
        val call = ApiService.instance.getAppData()
        call.enqueue(object : Callback<ArrayList<RewardsItem>> {
            override fun onResponse(
                call: Call<ArrayList<RewardsItem>>,
                response: Response<ArrayList<RewardsItem>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    rewardsList = response.body()!!
                    adapter = RewardsAdapter(rewardsList)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity, "No data found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<RewardsItem>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching data", t)
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
