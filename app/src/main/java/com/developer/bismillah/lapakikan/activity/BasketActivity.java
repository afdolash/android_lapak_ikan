package com.developer.bismillah.lapakikan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developer.bismillah.lapakikan.R;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
