package demo.solution.customdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       button= findViewById(R.id.Button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               CustomDialogBox();
           }
       });
    }

    private void CustomDialogBox() {
        AlertDialog.Builder  builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        View dialogview=inflater.inflate(R.layout.dialog_custom,null);
        builder.setView(dialogview);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextInputEditText textInputEditText=dialogview.findViewById(R.id.etInput);
                String input=textInputEditText.getText().toString();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //create and show dialog
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}