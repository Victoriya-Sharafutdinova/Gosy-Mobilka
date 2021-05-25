package com.example.gosy_mobilka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class FragmentActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Log.d(LOG_TAG, "FragmentActivity onCreate");
    }

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "FragmentActivity onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "FragmentActivity onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "FragmentActivity onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "FragmentActivity onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "FragmentActivity onDestroy");
    }


}