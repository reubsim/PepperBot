package com.example.guidance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Say;

public class map extends AppCompatActivity implements RobotLifecycleCallbacks {

    private QiContext qiContext;
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

    @Override
    protected void onResume() {
        super.onResume();
        if (qiContext != null) {
            mapGuidance();
        }
    }

    private void mapGuidance() {
        if (qiContext != null) {
            Say say = SayBuilder.with(qiContext)
                    .withText("Select location from the list below for guidance!")
                    .build();

            say.async().run();
        }
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    @Override
    public void onRobotFocusLost() {
        // Release the QiContext
        this.qiContext = null;
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // Handle focus refusal
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QiSDK.unregister(this, this);
    }
}