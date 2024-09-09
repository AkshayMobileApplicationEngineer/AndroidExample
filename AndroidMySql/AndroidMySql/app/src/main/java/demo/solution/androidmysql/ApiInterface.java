package demo.solution.androidmysql;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
        @GET("fetch_data.php")
        Call<List<DataModel>> getData();


}
