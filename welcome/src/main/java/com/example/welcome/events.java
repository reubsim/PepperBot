package com.example.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.actuation.Animation;

public class events extends AppCompatActivity implements RobotLifecycleCallbacks {

    private ListView lvEvents;
    private QiContext qiContext;
    private ArrayAdapter<String> eventsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        // Find the ListView by its ID
        lvEvents = findViewById(R.id.lvEvents);

        // Create an array of events
        String[] events = {
                "Extension of our partnership with GlobalFoundries",
                "Social Media For Your Career",
                "A Memorandum of Understanding (MOU) signed with ST Logistics",
                "Youth Corps Leaders Programme",
                "Beyond Basics with Chef Mirko Febbrile"
        };


        // Create an ArrayAdapter to populate the ListView with the array of events
        eventsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);

        // Set the ArrayAdapter as the adapter for the ListView
        lvEvents.setAdapter(eventsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (qiContext != null) {
            events();
        }
    }

    public void openEventsActivity(View view) {
        Intent intent = new Intent(this, events.class);
        startActivity(intent);
    }

    public void openFeedbackActivity(View view) {
        Intent intent = new Intent(this, feedback.class);
        startActivity(intent);
    }

    private void events() {
        if (qiContext != null) {
            Say say = SayBuilder.with(qiContext)
                    .withText("These are the current events held in RP.")
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