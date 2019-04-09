package com.midtermmad3125.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainCityActivity extends AppCompatActivity
{

    TextView textCity,textLat,textLong,textPop;
    Button weatherList;
    private JSONObject jsonObject;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCity = findViewById(R.id.textCity);
        textLat = findViewById(R.id.textLat);
        textLong = findViewById(R.id.textLong);
        textPop = findViewById(R.id.textPop);
        weatherList = findViewById(R.id.weather);
        getJsonData();


        weatherList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WeatherListActivity.class));
            }
        });


    }
    @SuppressLint("SetTextI18n")
    public void getJsonData (){
        String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        try {
            JSONObject cityData = new JSONObject(JsonData);
            JSONObject cityObject= cityData.getJSONObject("city");
            textCity.setText(cityObject.getString("name"));

            JSONObject cityCoordObject= cityObject.getJSONObject("coord");
            textLong.setText("Longitude: "+cityCoordObject.getString("lon"));
            textLat.setText("Latitude: "+cityCoordObject.getString("lat"));

            textPop.setText("population: "+cityObject.getString("population"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}