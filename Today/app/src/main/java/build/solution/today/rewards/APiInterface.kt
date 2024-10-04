package build.solution.today.rewards

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

const val baseUrl = "https://workholicpraveen.in/api/V34/"

interface ApiInterface {
    @POST("result/reward.php")
    fun getAppData(): Call<ArrayList<RewardsItem>>
}

// Create Retrofit object to instantiate the interface
object ApiService {
    val instance: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        instance = retrofit.create(ApiInterface::class.java)
    }
}
