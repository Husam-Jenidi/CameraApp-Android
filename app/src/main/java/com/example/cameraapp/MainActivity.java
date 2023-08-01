package com.example.cameraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button takePicBtn,cameraSettingBtn;
    ImageView imageViewInt,imageViewExt,imageViewExtSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final String LOG_TAG = MainActivity.class.getSimpleName();

        takePicBtn = findViewById(R.id.takePic);
        cameraSettingBtn = findViewById(R.id.takeSetting);
        imageViewInt = findViewById(R.id.imageViewInt);
        imageViewExt = findViewById(R.id.imageViewExt);
        imageViewExtSD = findViewById(R.id.imageViewExtSD);

        // Call the loadImageFromInternalStorage method from the ImageUtils class
        Bitmap bitmapInt = ClickActivity.ImageUtils.loadImageFromInternalStorage(this, "imageInt.jpg");

        if (bitmapInt != null) {
            // Set the Bitmap as the image source for the ImageView
            imageViewInt.setImageBitmap(bitmapInt);
        } else {
            // Image file not found, handle accordingly
            Toast.makeText(getApplicationContext(), "Image file not found in Int", Toast.LENGTH_SHORT).show();
        }

        //Call the loadImageFromExternalStorage method from the  class ClickActivity
        Bitmap bitmapExt = ClickActivity.ImageUtils.loadImageFromExternalStorage(this, "imageExt.jpg");

        if (bitmapExt != null) {
            // Set the Bitmap as the image source for the ImageView
            imageViewExt.setImageBitmap(bitmapExt);
        } else {
            Toast.makeText(getApplicationContext(), "Image file not found in Ext", Toast.LENGTH_SHORT).show();
        }

        //Call the loadImageFromExternalStorageSD method from the  class
        Bitmap bitmapExtSD = ClickActivity.ImageUtils.loadImageFromExternalSDStorage(this, "imageExtSD.jpg");

        if (bitmapExtSD != null) {
            // Set the Bitmap as the image source for the ImageView
            imageViewExtSD.setImageBitmap(bitmapExtSD);
        } else {
            // Image file not found, handle accordingly
            Toast.makeText(getApplicationContext(), "Image file not found in Ext", Toast.LENGTH_SHORT).show();
        }

        //the open cameraSettingBtn button//
        cameraSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, settingPart1.class);
                startActivity(intent);
            }
        });
        //the open cameraSettingBtn button//
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ClickActivity.class);
                startActivity(intent);
            }
        });
    }

    private static final String LOG_TAG =
           MainActivity.class.getSimpleName();
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
        }

    @Override
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