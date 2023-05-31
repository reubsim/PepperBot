package com.example.guidance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Say;

public class entertainment extends AppCompatActivity implements RobotLifecycleCallbacks {

    private Button btnJoke;
    private Button btnTrivia;
    private QiContext qiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        btnJoke = findViewById(R.id.buttonJoke);
        btnTrivia = findViewById(R.id.buttonTrivia);

        // Register the RobotLifecycleCallbacks to get the QiContext
        QiSDK.register(this, this);

        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        btnTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellTrivia();
            }
        });
    }

    private void tellJoke() {
        if (qiContext != null) {
            Say say = SayBuilder.with(qiContext)
                    .withText("Why don't scientists trust atoms? Because they make up everything!")
                    .build();

            say.async().run();
        }
    }

    private void tellTrivia() {
        if (qiContext != null) {
            Say say = SayBuilder.with(qiContext)
                    .withText("RP is the first poly to adopt the Problem-based Learning method, encouraged students to learn by thinking out of the box to devise innovative solutions.")
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