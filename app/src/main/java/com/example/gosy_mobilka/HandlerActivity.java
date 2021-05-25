package com.example.gosy_mobilka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class HandlerActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    HandlerActivity h;
    TextView tvInfo;
    Button btnStart;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnStart = (Button) findViewById(R.id.btnStart);
        h = new HandlerActivity() {
            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                tvInfo.setText("Закачано файлов: " + msg.what);
                if (msg.what == 10) btnStart.setEnabled(true);
            };
        };
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            // долгий процесс
                            downloadFile();
                            // обновляем TextView
                            tvInfo.setText("Закачано файлов: " + i);
                            // пишем лог
                            Log.d(LOG_TAG, "i = " + i);
                        }
                    }
                });
                t.start();
                break;
            case R.id.btnTest:
                Log.d(LOG_TAG, "test");
                break;
            default:
                break;
        }
    }

    void downloadFile() {
        // пауза - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}