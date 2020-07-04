package com.hafilah.ta_restoran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Constants for the notification actions buttons.
    private static final String ACTION_UPDATE_NOTIFICATION = "com.hafilah.ta_restoran.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    private final String COLOR_KEY = "color";

    private TextView mShowTextView;
    private int mColor;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.hafilah.ta_restoran";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

        mShowTextView = findViewById(R.id.nmprofil);
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowTextView.setBackgroundColor(mColor);

    }

    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowTextView.setBackgroundColor(color);
        mColor = color;
    }

    public void reset(View view){
        // Reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowTextView.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }

    public void next(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
