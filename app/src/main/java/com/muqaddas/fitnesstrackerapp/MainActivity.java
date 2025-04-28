package com.muqaddas.fitnesstrackerapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {

    private TextView stepCounterText, distanceText, caloriesText;
    private Button startButton, stopButton;
    private int steps = 0;
    private boolean isTracking = false;
    private Handler handler = new Handler();
    private Runnable stepRunnable;

    private static final double STEP_LENGTH_METERS = 0.7;  // Average step length in meters (adjustable)
    private static final double CALORIES_PER_STEP = 0.04;  // Average calories burnt per step (adjustable)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepCounterText = findViewById(R.id.step_counter_text);
        distanceText = findViewById(R.id.distance_text);
        caloriesText = findViewById(R.id.calories_text);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFakeTracking();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopFakeTracking();
            }
        });
    }

    private void startFakeTracking() {
        if (!isTracking) {
            isTracking = true;
            stepRunnable = new Runnable() {
                @Override
                public void run() {
                    if (isTracking) {
                        steps++;
                        updateUI();
                        handler.postDelayed(this, 1000); // Update every second
                    }
                }
            };
            handler.post(stepRunnable);
        }
    }

    private void stopFakeTracking() {
        isTracking = false;
        handler.removeCallbacks(stepRunnable);
    }

    private void updateUI() {
        stepCounterText.setText("Steps: " + steps);

        double distanceInMeters = steps * STEP_LENGTH_METERS;
        double distanceInKm = distanceInMeters / 1000;  // meters to km
        distanceText.setText(String.format("Distance: %.2f km", distanceInKm));

        double caloriesBurned = steps * CALORIES_PER_STEP;
        caloriesText.setText(String.format("Calories: %.2f kcal", caloriesBurned));
    }
}
