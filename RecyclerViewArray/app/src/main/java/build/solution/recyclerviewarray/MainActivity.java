package build.solution.recyclerviewarray;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import build.solution.recyclerviewarray.MyAdapter;
import build.solution.recyclerviewarray.R;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Inflate the correct layout
        recyclerView = findViewById(R.id.mainRecycle);  // Make sure this ID matches your XML layout

        data = new String[]{"Rohit", "Mohit", "Sankar", "Geeta", "Pawan", "Rohan", "Sohan", "Sanjay", "Vishal", "Mohan"};

        // Set up the layout manager for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the adapter with the data array
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }

}
