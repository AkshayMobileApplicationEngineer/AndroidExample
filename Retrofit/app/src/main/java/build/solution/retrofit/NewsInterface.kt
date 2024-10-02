package build.solution.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Constants for base URL and API key
const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "ab99fae229564cbba41156f2094f6485"

// Interface for API endpoints
interface NewsInterface {

    // Get top headlines with country and page query parameters
    @GET("top-headlines?apiKey=$API_KEY")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int
    ): Call<News>
}

// Create Retrofit object to instantiate the interface
object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder() // Corrected from Bulider() to Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Added converter for JSON to object mapping
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}
