package demo.solution.customtoast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Ensure this runs on the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.cusom_toast, findViewById(R.id.toast_toast_design));

                        TextView text = view.findViewById(R.id.toast_text);
                        text.setText("This is a custom Toast");

                        ImageView image = view.findViewById(R.id.ic_toast);
                        image.setImageResource(R.drawable.loho);  // Assuming the correct resource name is 'logo'

                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(view);
                        toast.show();
                    }
                });
            }
        }, 3000);
    }
}
