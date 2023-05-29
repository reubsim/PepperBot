package com.example.guidance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class map extends AppCompatActivity {

    private ListView lvLocations;
    private ArrayAdapter<String> locationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Find the ListView by its ID
        lvLocations = findViewById(R.id.lvLocations);

        // Create an array of locations
        String[] locations = {"Republic Polytechnic Centre (RPC)", "The Republic Cultural Centre (TRCC)", "SIT@RP Building", "RPIC"};

        // Create an ArrayAdapter to populate the ListView with the array of locations
        locationsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);

        // Set the ArrayAdapter as the adapter for the ListView
        lvLocations.setAdapter(locationsAdapter);
    }
}