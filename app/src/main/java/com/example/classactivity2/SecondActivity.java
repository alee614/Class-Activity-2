package com.example.classactivity2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView city;
    private TextView description;
    private TextView feels_like;
    private TextView low_temp;
    private TextView high_temp;
    private TextView country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        city = findViewById(R.id.city);
        description = findViewById(R.id.weather);
        feels_like = findViewById(R.id.feels_temp);
        low_temp = findViewById(R.id.low_temp);
        high_temp = findViewById(R.id.high_temp);
        country = findViewById(R.id.country);

/**
        intent.putExtra("name", json.getString("name"));
        intent.putExtra("feels_like", json.getString("main.feels_like"));
        intent.putExtra("min_temp", json.getString("main.temp_min"));
        intent.putExtra("max_temp", json.getString("main.temp_max"));
        intent.putExtra("description", json.getString("description"));
 */

        Intent intent = getIntent();
        city.setText(intent.getStringExtra("name"));
        country.setText(intent.getStringExtra("country"));
        description.setText(intent.getStringExtra("description"));
        feels_like.setText(intent.getStringExtra("feels_like"));
        low_temp.setText(intent.getStringExtra("min_temp"));
        high_temp.setText(intent.getStringExtra("max_temp"));



    }
}