package com.developer.bismillah.lapakikan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developer.bismillah.lapakikan.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
