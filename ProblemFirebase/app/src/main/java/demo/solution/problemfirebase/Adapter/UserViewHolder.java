package demo.solution.problemfirebase.Adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import demo.solution.problemfirebase.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private TextView usernameTextView;
    private TextView emailTextView;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        usernameTextView = itemView.findViewById(R.id.username_text);
        emailTextView = itemView.findViewById(R.id.email_text);
    }

    // Method to bind data
    public void setUserData(String username, String email) {
        usernameTextView.setText(username);
        emailTextView.setText(email);
    }
}
