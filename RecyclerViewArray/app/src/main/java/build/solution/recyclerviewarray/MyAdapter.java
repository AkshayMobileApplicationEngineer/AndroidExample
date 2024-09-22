package build.solution.recyclerviewarray;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
     String[] data;

    // Constructor to pass the data (array of names)
    public MyAdapter(String[] data) {
        this.data = data;
    }

    // Inflate the item layout for each item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item, parent, false);
        return new ViewHolder(view);
    }

    // Bind the data to each item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(data[position]);
        Log.e("TAG", "onBindViewHolder: "+data.toString());
    }

    // Return the total number of items
    @Override
    public int getItemCount() {
        return data.length;
    }

    // Define the ViewHolder for item layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
