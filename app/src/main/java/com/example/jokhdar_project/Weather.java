package com.example.jokhdar_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Weather extends AppCompatActivity {

    String url;
    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView temperature, description, humidity;
    Button btnChange;
    EditText enterCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        SharedPreferences sp = getSharedPreferences("myKey", MODE_PRIVATE);
        // JSON object that contains weather information

        //link graphical items to variables
        temperature = findViewById(R.id.temperature);
        description = findViewById(R.id.description);
        humidity = findViewById(R.id.humidity);
        btnChange = findViewById(R.id.pickCityBtn);
        enterCity = findViewById(R.id.pickCity);
        String value = sp.getString("weather_url","");
        weather(value);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clicked;
                clicked = enterCity.getText().toString();
                url = "http://api.openweathermap.org/data/2.5/weather?q="+clicked+"&appid=99544f0c6d4f86c6a25d7b3021204c1f&units=metric";

                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sp.edit();
                editor.putString("weather_url", url);
                editor.apply();
                String value = sp.getString("weather_url","");
                weather(value);
            }
        });
        weather(url);
        weatherBackground = findViewById(R.id.weatherbackground);

    }

    public void weather(String url){
        @SuppressLint("SetTextI18n") JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            Log.d("Eisa", "Response Received");
            Log.d("Eisa", response.toString());
            try {
                JSONObject jsonMain = response.getJSONObject("main");

                int temp = jsonMain.getInt("temp");
                Log.d("Eisa","temp=" + temp);
                temperature.setText(String.valueOf(temp)+"°C");


                int humid = jsonMain.getInt("humidity");
                Log.d("Eisa","humidity=" + humid);
                humidity.setText("Humidity: "+String.valueOf(humid)+"%");

                String townResponse = response.getString("name");
                description.setText(townResponse);

                JSONArray jsonArray = response.getJSONArray("weather");
                for (int i=0; i<jsonArray.length();i++){
                    Log.d("Eisa-array",jsonArray.getString(i));
                    JSONObject oneObject = jsonArray.getJSONObject(i);
                    String weather =
                            oneObject.getString("main");
                    Log.d("Eisa-w",weather);

                    switch (weather) {
                        case "Clear":
                            Glide.with(Weather.this)
                                    .load("data:image/jpeg;base64,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")
                                    .into(weatherBackground);
                            break;
                        case "Clouds":
                            Glide.with(Weather.this)
                                    .load("https://images.theconversation.com/files/228393/original/file-20180719-142423-4065mr.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=675.0&fit=crop")
                                    .into(weatherBackground);
                            break;
                        case "Rain":
                            Glide.with(Weather.this)
                                    .load("https://samitivej-prod-new-website.s3.ap-southeast-1.amazonaws.com/public/uploads/descriptions/eeece6f94472507eff65a7277be708b6.jpg")
                                    .into(weatherBackground);
                            break;
                    }

                }
            }
            catch (JSONException e){
                e.printStackTrace();
                Log.e("Receive Error", e.toString());
            }
        }, error -> Log.d("Eisa", "Error Retrieving URL"));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}