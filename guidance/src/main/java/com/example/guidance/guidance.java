package com.example.guidance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class guidance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
    }

    public void openMapActivity(View view) {
        Intent intent = new Intent(this, map.class);
        startActivity(intent);
    }

    public void openEntertainActivity(View view) {
        Intent intent = new Intent(this, entertainment.class);
        startActivity(intent);
    }
}