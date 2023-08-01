package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;

public class settingPart1 extends AppCompatActivity {
    private Button returnBtn, setting2Btn, testListener, SaveBtn, ReadBtn;
    TextView detector, show_text;
    private String filename = "saved_text.txt";
    EditText textedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_part1);

        returnBtn = findViewById(R.id.return1);
        setting2Btn = findViewById(R.id.setting2);
        testListener = findViewById(R.id.testListener);
        detector = findViewById(R.id.detector);

        SaveBtn = findViewById(R.id.Save);
        ReadBtn = findViewById(R.id.Read);
        show_text = findViewById(R.id.show_text);
        textedit = findViewById(R.id.textedit);


        // the save button //
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textedit.getText().toString();

                saveTextToFile(text);

                Toast.makeText(getApplicationContext(), "Text saved to file!", Toast.LENGTH_SHORT).show();

            }
        });

        // the Read button //
        ReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = readTextFromFile();
                show_text.setText(text);
            }
        });

        // the return button //
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(settingPart1.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //the setting2 button//

        setting2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(settingPart1.this, settingPart2.class);
                startActivity(intent);
            }
        });
        //the test listener button//
        testListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detector.setText("Click Event");
            }
        });

        //the long test listener button//
        testListener.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                detector.setText("Long Click Event");
                return true;
            }
        });
    }
    // save text to file //



    private void saveTextToFile(String text) {

        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read text from file //
    private String readTextFromFile() {
        String text = "";
        try {
            FileInputStream fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line=br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            text = sb.toString();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }




    private static final String LOG_TAG =
            settingPart1.class.getSimpleName();
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
