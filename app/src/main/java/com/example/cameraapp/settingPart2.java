package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class settingPart2 extends AppCompatActivity {
    private Button setting1Btn, setting3Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_part2);
        setting1Btn= findViewById(R.id.setting1);
        setting3Btn= findViewById(R.id.setting3);
        //the return button//
        setting1Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(settingPart2.this, settingPart1.class);
                    startActivity(intent);
                }
            });
        //the return button//
        setting3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(settingPart2.this, settingPart3.class);
                startActivity(intent);
            }
        });

    }
    private static final String LOG_TAG =
            settingPart2.class.getSimpleName();
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");

    }
}