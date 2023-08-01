package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClickActivity extends AppCompatActivity {
    ImageView imageView1;
    Button click,saveINTBTN,saveEXTBTN,saveEXTSDBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        imageView1 = findViewById(R.id.imageView1);
        click=findViewById(R.id.click);

        saveINTBTN =findViewById(R.id.saveINTBTN);
        saveEXTBTN=findViewById(R.id.saveEXTBTN);
        saveEXTSDBTN=findViewById(R.id.saveEXTSDBTN);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 1);

            }
        });
        //the save in internal storage button//
        saveINTBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable) imageView1.getDrawable()).getBitmap();
                String filename = "imageInt.jpg";
                File file = new File(getFilesDir(), filename);
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                    Toast.makeText(getApplicationContext(), "Image saved to internal storage", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // the save in external storage button//
        saveEXTBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImageToExternalStorage();
            }
            private void saveImageToExternalStorage() {
                Bitmap imageBitmap = ((BitmapDrawable) imageView1.getDrawable()).getBitmap();
                if (imageBitmap == null) {
                    Toast.makeText(getApplicationContext(), "No image available", Toast.LENGTH_SHORT).show();
                    return;
                }

                File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(getApplicationContext(), Environment.DIRECTORY_PICTURES);
                if (externalStorageVolumes.length >= 1) {
                    File file = new File(externalStorageVolumes[0], "imageExt.jpg");

                    String state = Environment.getExternalStorageState(externalStorageVolumes[0]);
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        long freeSpace = externalStorageVolumes[0].getFreeSpace();
                        long freeSpaceInMB = freeSpace / (1024 * 1024);

                        Toast.makeText(getApplicationContext(), "External storage is available", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Free space: " + freeSpaceInMB + " MB", Toast.LENGTH_SHORT).show();

                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();

                            Toast.makeText(getApplicationContext(), "Image saved to external storage", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed to save image to external storage", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "External storage is not ready", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "External storage not available", Toast.LENGTH_SHORT).show();
                }
            }

        });
        //the save in external storage SD button//
        saveEXTSDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImageToExternalStorageSD();
            }
            private void saveImageToExternalStorageSD() {
                Bitmap imageBitmap = ((BitmapDrawable) imageView1.getDrawable()).getBitmap();
                if (imageBitmap == null) {
                    Toast.makeText(getApplicationContext(), "No image available", Toast.LENGTH_SHORT).show();
                    return;
                }

                File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(getApplicationContext(), Environment.DIRECTORY_PICTURES);
                if (externalStorageVolumes.length >= 2) {
                    File file = new File(externalStorageVolumes[1], "imageExtSD.jpg");

                    String state = Environment.getExternalStorageState(externalStorageVolumes[1]);
                    if (Environment.MEDIA_MOUNTED.equals(state)) {
                        long freeSpace = externalStorageVolumes[1].getFreeSpace();
                        long freeSpaceInMB = freeSpace / (1024 * 1024);

                        Toast.makeText(getApplicationContext(), "SD card storage is available", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Free space: " + freeSpaceInMB + " MB", Toast.LENGTH_SHORT).show();

                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();

                            Toast.makeText(getApplicationContext(), "Image saved to SD card storage", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Failed to save image to SD card storage", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "SD card storage is not ready", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "SD card storage not available", Toast.LENGTH_SHORT).show();
                }
            }

        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // handle the click event
                Toast.makeText(getApplicationContext(), "long click to delete!", Toast.LENGTH_SHORT).show();
            }
        });
        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageView1.setImageBitmap(null);
            return true;
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (data != null) {
               // Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imageView1.setImageBitmap((Bitmap) data.getExtras().get("data"));
                    Context context = getApplicationContext();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                    String clickTime = sdf.format(new Date());
                    CharSequence text = "Click Event Happened on " + clickTime + "!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {

                }
            } else {
                finish();
            }
    }
    public static class ImageUtils {
        //Load Image from Internal //

        public static Bitmap loadImageFromInternalStorage(Context context, String fileName) {
            // Retrieve the file path of the saved image
            String filePath = context.getFilesDir() + File.separator + fileName;

            // Create a File object from the file path
            File file = new File(filePath);

            // Check if the file exists
            if (file.exists()) {
                // Load the image into a Bitmap object
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }

            return null;
        }
        //Load Image from External //

        public static Bitmap loadImageFromExternalStorage(Context context, String fileName) {
            File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(context, Environment.DIRECTORY_PICTURES);
            File file = new File(externalStorageVolumes[0], fileName);

            if (file.exists()) {
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }

            return null;
        }
        //Load Image from External SD //
        public static Bitmap loadImageFromExternalSDStorage(Context context, String fileName) {
            File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(context, Environment.DIRECTORY_PICTURES);
            File file = new File(externalStorageVolumes[1], fileName);

            if (file.exists()) {
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }

            return null;
        }

    }





}


