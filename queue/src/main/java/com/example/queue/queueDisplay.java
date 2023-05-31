package com.example.queue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Queue;

public class queueDisplay extends AppCompatActivity {

    EditText etPhoneNum;
    Button btnPhoneNum;
    Queue<Integer> queue;
    int currentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_display);

        etPhoneNum = findViewById(R.id.PhoneNum);
        btnPhoneNum = findViewById(R.id.btnSubmit);
        currentNumber = 0;

        btnPhoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber++;
                queue.add(currentNumber);

                String phoneNumber = etPhoneNum.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, String.valueOf(queue), null, null);
            }
        });

    }
}