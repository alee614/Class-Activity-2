package com.example.classactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private String city;
    private TextInputEditText input_city;
    private Button go;
    private static final String api_url = "http://api.openweathermap.org/data/2.5/weather";

    AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_city = findViewById(R.id.input_city);
        go = findViewById(R.id.button_Go);

        go.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // pull the information from the textinput
                // set the RequestParams
                searchWeather(v);

            }
        });



    }

    public void searchWeather(View view){
        // get information from RequestParams correctly
        city = input_city.getText().toString();
        Log.d("city:", city);

        RequestParams params = new RequestParams();
        params.put("q", city);
        params.put("appid", "4405eac90732bc35c793b1c4d5db3f77");
        params.put("units", "imperial");


        client.get(api_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    // need to get JSON object lmao, pain in the ass
                    JSONObject json = new JSONObject(new String(responseBody));

                    JSONArray weather = json.getJSONArray("weather");
                    JSONObject description = weather.getJSONObject(0);

                    JSONObject sys = json.getJSONObject("sys");

                    JSONObject main = json.getJSONObject("main");

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    intent.putExtra("name", json.getString("name"));
                    intent.putExtra("country", sys.getString("country"));
                    intent.putExtra("description", description.getString("description"));
                    intent.putExtra("feels_like", main.getString("feels_like"));
                    intent.putExtra("min_temp", main.getString("temp_min"));
                    intent.putExtra("max_temp", main.getString("temp_max"));


                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // if fails, then go to Third activity page aka error
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);

            }
        });





    }
}