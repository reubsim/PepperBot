package com.example.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.actuation.Animation;

public class welcome extends AppCompatActivity implements RobotLifecycleCallbacks {

    private QiContext qiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (qiContext != null) {
            wave();
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

    private void wave() {
        if (qiContext != null) {
            Say say = SayBuilder.with(qiContext)
                    .withText("Hello, Welcome to RP One-Stop Centre")
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