package com.example.queue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.actuation.Animation;

public class queue extends AppCompatActivity implements RobotLifecycleCallbacks {

    TextView tvSA, tvSC, tvFA, tvIT, tvOthers, tvIS;
    private QiContext qiContext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        tvSA = findViewById(R.id.sa);
        tvSC = findViewById(R.id.sc);
        tvFA = findViewById(R.id.fa);
        tvIS = findViewById(R.id.is);
        tvOthers = findViewById(R.id.others);
        tvIT = findViewById(R.id.it);

        tvSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SA = new Intent(queue.this,
                        queueDisplay.class);
                startActivity(SA);
            }
        });

        tvSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SC = new Intent(queue.this,
                        queueDisplay.class);
                startActivity(SC);
            }
        });

        tvFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FA = new Intent(queue.this,
                        queueDisplay.class);
                startActivity(FA);
            }
        });

        tvIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IS = new Intent(queue.this,
                        queueDisplay.class);
                startActivity(IS);
            }
        });

        tvOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent others = new Intent(queue.this,
                        queueDisplay.class);
                startActivity(others);
            }
        });

        tvIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IT = new Intent(queue.this,
                        queueDisplay.class);
                startActivity(IT);
            }
        });

        tvOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent others = new Intent(queue.this,
                        queueDisplay.class);
                startActivity(others);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (qiContext != null) {
            queueSpeech();
        }
    }
    private void queueSpeech() {
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