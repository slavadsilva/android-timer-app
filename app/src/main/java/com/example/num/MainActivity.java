package com.example.num;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView timeResult;
    MaterialButton buttonStart, buttonStop, buttonReset;
    Runnable runnable;
    int seconds = 0;
    Handler handler = new Handler();
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeResult = findViewById(R.id.time);
        buttonStart = findViewById(R.id.button_start);
        buttonStop = findViewById(R.id.button_stop);
        buttonReset = findViewById(R.id.button_reset);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "Start":
                if (!isRunning) {
                    startTimer();
                }
                break;
            case "Stop":
                if (isRunning) {
                    stopTimer();
                }
                break;
            case "Reset":
                resetTimer();
                break;
        }
    }

    private void startTimer() {
        isRunning = true;
        runnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
                timeResult.setText(time);

                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }

    private void stopTimer() {
        isRunning = false;
        handler.removeCallbacks(runnable);
    }

    private void resetTimer() {
        stopTimer();
        seconds = 0;
        timeResult.setText("00:00:00");
    }
}
