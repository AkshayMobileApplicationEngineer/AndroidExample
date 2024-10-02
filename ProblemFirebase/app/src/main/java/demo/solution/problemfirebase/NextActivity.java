package demo.solution.problemfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import demo.solution.problemfirebase.Adapter.UserViewHolder;
import demo.solution.problemfirebase.Model.User1;

import android.view.LayoutInflater;

public class NextActivity extends AppCompatActivity {
    private AppCompatButton appCompatButton;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<User1, UserViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Initialization
        appCompatButton = findViewById(R.id.show_data_button);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Button click listener to load data
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load data into RecyclerView
                loadDataIntoRecyclerView();
            }
        });
    }

    private void loadDataIntoRecyclerView() {
        // Query to fetch data from Firebase Realtime Database
        Query query = FirebaseDatabase.getInstance().getReference().child("users");

        // FirebaseRecyclerOptions to configure FirebaseRecyclerAdapter
        FirebaseRecyclerOptions<User1> options = new FirebaseRecyclerOptions.Builder<User1>()
                .setQuery(query, User1.class)
                .build();

        // FirebaseRecyclerAdapter
        adapter = new FirebaseRecyclerAdapter<User1, UserViewHolder>(options) {
            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Inflate user_item layout for each item
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User1 model) {
                // Bind User1 data to ViewHolder
                holder.setUserData(model.getUsername(), model.getEmail());
                Log.d("TAG", "User data: " + model.getUsername() + " : " + model.getEmail());
            }

            @Override
            public void onError(@NonNull DatabaseError error) {
                // Handle possible errors
                Toast.makeText(NextActivity.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onCancelled: " + error.getMessage());
            }
        };

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(adapter);

        // Start listening for Firebase updates
        adapter.startListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
