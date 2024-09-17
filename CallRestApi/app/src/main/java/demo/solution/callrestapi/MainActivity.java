package demo.solution.callrestapi;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail;
    Button buttonLogin;
    TextView textViewStatus;

    // Replace with your machine's IP address
    private static final String URL_link = "http://192.168.29.210/api/user_api.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findByID();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processdata(editTextName.getText().toString(), editTextEmail.getText().toString());
            }
        });
    }

    private void findByID() {
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewStatus = findViewById(R.id.textViewStatus);
    }

    private void processdata(final String name, final String email) {
        // Create the JSON object with the user input
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        JSONObject jsonObject = new JSONObject(params);

        // Create a JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, URL_link, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the response from the server
                            String message = response.getString("message");
                            textViewStatus.setText("Response: " + message);
                        } catch (JSONException e) {
                            textViewStatus.setText("JSON Error: " + e.getMessage());
                            Log.e(TAG, "JSON Parsing Error: ", e);
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            textViewStatus.setText("Timeout error: The server is taking too long to respond.");
                        } else if (error instanceof NoConnectionError) {
                            textViewStatus.setText("No connection error: Check your network connection.");
                        } else if (error instanceof AuthFailureError) {
                            textViewStatus.setText("Authentication failure error: Check your API credentials.");
                        } else if (error instanceof ServerError) {
                            textViewStatus.setText("Server error: The server responded with an error.");
                        } else if (error instanceof NetworkError) {
                            textViewStatus.setText("Network error: There was a problem with the network.");
                        } else if (error instanceof ParseError) {
                            textViewStatus.setText("Parse error: Failed to parse the server response.");
                        } else {
                            textViewStatus.setText("Unknown error occurred.");
                        }

                        Log.e(TAG, "Error: ", error);
                        if (error.networkResponse != null) {
                            Log.e(TAG, "Status Code: " + error.networkResponse.statusCode);
                            Log.e(TAG, "Response Data: " + new String(error.networkResponse.data));
                        }


                    }

                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Set a retry policy with a longer timeout
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000, // Timeout in milliseconds (20 seconds)
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }
}
