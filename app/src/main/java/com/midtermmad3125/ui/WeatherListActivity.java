package com.midtermmad3125.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.midtermmad3125.CustomAdapter;
import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity
{

    ArrayList<String> date = new ArrayList<>();
    ArrayList<Double> minimumTemp = new ArrayList<>();
    ArrayList<Double> maxTemp = new ArrayList<>();
    ArrayList<String> wdetails = new ArrayList<>();
    ArrayList<String> wdescription = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        loadJson();

        CustomAdapter customAdapter = new CustomAdapter(WeatherListActivity.this, date, minimumTemp, maxTemp, wdetails, wdescription);
        recyclerView.setAdapter(customAdapter);
    }

    private void loadJson() {

        //String JsonData = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");
        try{
            //JSONObject cityData = new JSONObject(JsonData);
            //JSONObject cityObj= cityData.getJSONObject("city");


            JSONObject mObj = new JSONObject(loadJSONFromAsset());
            JSONObject cityObj = mObj.getJSONObject("city");

            JSONArray list = mObj.getJSONArray("list");

            for(int i = 0; i < list.length(); i++) {
                JSONObject dObj = list.getJSONObject(i);
                date.add(ReadJSONUtils.getDateFromTimeStamp(dObj.getLong("dt")));


                //Object For Temp Details
                JSONObject tempObj = dObj.getJSONObject("temp");
                minimumTemp.add(tempObj.getDouble("min"));
                maxTemp.add(tempObj.getDouble("max"));


                //Array for Weather Details
                JSONArray weatherObj = dObj.getJSONArray("weather");
                for(int j = 0; j < weatherObj.length(); j++) {
                    JSONObject wObj = weatherObj.getJSONObject(j);
                    wdetails.add(wObj.getString("main"));
                    wdescription.add(wObj.getString("description"));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("moscow_weather.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}