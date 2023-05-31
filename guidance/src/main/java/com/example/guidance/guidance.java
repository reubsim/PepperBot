package com.example.guidance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Say;
public class guidance extends AppCompatActivity implements RobotLifecycleCallbacks {

    private QiContext qiContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (qiContext != null) {
            tellGuidance();
        }
    }

    public void openMapActivity(View view) {
        Intent intent = new Intent(this, map.class);
        startActivity(intent);
    }

    public void openEntertainActivity(View view) {
        Intent intent = new Intent(this, entertainment.class);
        startActivity(intent);
    }

    private void tellGuidance() {
        if (qiContext != null) {
            Say say = SayBuilder.with(qiContext)
                    .withText("Hello I'm Pepper and I'm here to assist you! Kindly select one of the options on my tablet")
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