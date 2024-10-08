package build.solution.recyclerviewlist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<String> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerviewmain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList=new ArrayList<>();
        for(int i=0;i<=30;i++){
            itemList.add("item"+1);
        }
        myAdapter=new MyAdapter(itemList);
        recyclerView.setAdapter(myAdapter);


    }
}