package build.solution.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NewAdapter
    private lateinit var newListRcv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newListRcv = findViewById(R.id.newListRcv)

        // Set up the RecyclerView's layout manager
        newListRcv.layoutManager = LinearLayoutManager(this)

        // Fetch news data
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("us", 1)
        news.enqueue(object : Callback<News?> {
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                val newsResponse = response.body()
                if (newsResponse != null) {
                    Log.d("TAG", "News data: ${newsResponse.toString()}")
                    Log.e("TAG", "onResponse: ${newsResponse.toString()}")

                    // RecyclerView Setup with the list of articles from the news response
                    adapter = NewAdapter(this@MainActivity, newsResponse.articles)
                    newListRcv.adapter = adapter
                    newListRcv.layoutManager=LinearLayoutManager(this@MainActivity)
                } else {
                    Log.d("TAG", "No news received")
                }
            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                Log.e("TAG", "Error in fetching news", t)
            }
        })
    }
}
