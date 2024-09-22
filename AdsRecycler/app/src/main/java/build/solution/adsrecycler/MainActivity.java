package build.solution.adsrecycler;

import android.media.tv.AdRequest;
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

import build.solution.adsrecycler.Adapter.Adapter;
import build.solution.adsrecycler.Model.Model;

public class MainActivity extends AppCompatActivity {
    public static int  ads_per_item =4;
    private static final String banner_ads_id="";
    private List<Object> recycler_item=new ArrayList<>();


    RecyclerView recyclerView;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
       getSupportActionBar().hide();
        Object MobileAds = null;



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataItem();
        getBanneerItem();
        LoadAdsBanner();

        adapter=new Adapter(recycler_item);
        recyclerView.setAdapter(adapter);
    }

    private void getDataItem() {
        Model model=new Model();
        model.getHeader("C Programming language");
        model.getDescription("Desktop programing language");
        model.getImageName(R.drawable.u);
        recycler_item.add(model);


        Model model2=new Model();
        model2.getHeader("Java Programming language");
        model2.getDescription("Desktop programing language");
        model2.getImageName(R.drawable.u);
        recycler_item.add(model2);



        Model model3=new Model();
        model3.getHeader("Java Programming language");
        model3.getDescription("Desktop programing language");
        model3.getImageName(R.drawable.u);
        recycler_item.add(model3);


        Model model4=new Model();
        model4.getHeader("Java Programming language");
        model4.getDescription("Desktop programing language");
        model4.getImageName(R.drawable.u);
        recycler_item.add(model4);


        Model model5=new Model();
        model5.getHeader("Java Programming language");
        model5.getDescription("Desktop programing language");
        model5.getImageName(R.drawable.u);
        recycler_item.add(model5);


        Model model6=new Model();
        model6.getHeader("Java Programming language");
        model6.getDescription("Desktop programing language");
        model6.getImageName(R.drawable.u);
        recycler_item.add(model6);

        Model model7=new Model();
        model7.getHeader("Java Programming language");
        model7.getDescription("Desktop programing language");
        model7.getImageName(R.drawable.u);
        recycler_item.add(model7);


        Model model8=new Model();
        model8.getHeader("Java Programming language");
        model8.getDescription("Desktop programing language");
        model8.getImageName(R.drawable.u);
        recycler_item.add(model8);




        Model model9=new Model();
        model9.getHeader("Java Programming language");
        model9.getDescription("Desktop programing language");
        model9.getImageName(R.drawable.u);
        recycler_item.add(model9);



    }

    private void getBanneerItem() {
        for(int i=0;i<recycler_item.size();i=i+ads_per_item)
        {
            AdView adView=new AdView(MainActivity.this);
            adView.setAdSize(AdSize.Banner);
            adView.SetAdUnitID(banner_ads_id);
            recycler_item.add(adView);
        }
    }

    private void LoadAdsBanner()
    {
        for(int i=0;i<recycler_item.size();i++)
        {
            Object items = recycler_item.get(i);
            if(items instanceof AdView)
            {
                final  Adview adview=(Adview) items;
                adview.loaded(new AdRequest.Build().build());
            }
        }
    }
}