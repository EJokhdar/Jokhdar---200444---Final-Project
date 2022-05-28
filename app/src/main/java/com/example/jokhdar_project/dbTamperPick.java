package com.example.jokhdar_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dbTamperPick extends AppCompatActivity {

    Button addDB, updateDB, deleteDB;
    ImageView weatherBackground;
    TextView temperature, humidity, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_tamper_pick);

        weatherBackground = findViewById(R.id.weatherImgDBAct);
        temperature = findViewById(R.id.cityTempDBAct);
        humidity = findViewById(R.id.cityHumidityDBAct);
        description = findViewById(R.id.cityNameDBActs);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("weather_url","");
        weather(value);

        addDB = findViewById(R.id.addDB);
        addDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dbTamperPick.this, addToDB.class));
            }
        });

        updateDB = findViewById(R.id.updateDB);
        updateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dbTamperPick.this, updateData.class));
            }
        });

        deleteDB = findViewById(R.id.deleteDB);
        deleteDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dbTamperPick.this, deleteData.class));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("weather_url","");
        weather(value);
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
                            weatherBackground.setImageResource(R.drawable.clear);
                            break;
                        case "Clouds":
                            weatherBackground.setImageResource(R.drawable.clouds);
                            break;
                        case "Rain":
                            weatherBackground.setImageResource(R.drawable.thunderstorm);
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