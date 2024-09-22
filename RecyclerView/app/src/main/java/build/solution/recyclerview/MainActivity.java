package build.solution.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import build.solution.recyclerview.Adapter.MainRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainRecyclerViewAdapter mainRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.logo_background_color));


        recyclerView=findViewById(R.id.Recycler_view_item_main);

        String arr[]={"Amar","Geeta","Sandeep","Reena","Pankaj","Monika","Anamikka","Pawan"};

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainRecyclerViewAdapter(arr));

    }
}