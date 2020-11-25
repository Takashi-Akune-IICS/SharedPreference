package com.akune.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Screen2 extends AppCompatActivity {
    EditText etComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
    }

    public void logout(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveComment(View v) {
        FileOutputStream os = null;
        etComments = findViewById(R.id.et_Comment);
        String str = etComments.getText().toString();
        byte[] comment = str.getBytes();

        try {
            os = openFileOutput("comment.txt", Context.MODE_PRIVATE);
            os.write(comment);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "comment saved", Toast.LENGTH_SHORT).show();
    }

    public void readComment(View v) {
        FileInputStream is = null;
        etComments = findViewById(R.id.et_Comment);
        try {
            is = openFileInput("comment.txt");
            int c;
            StringBuffer buffer = new StringBuffer();
            while ((c = is.read()) != -1) {
                buffer.append((char) c);
            }
            etComments.setText(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveExternal(View v) {
        File file = new File(getExternalFilesDir(null), "comment2.txt");
        FileOutputStream os = null;

        etComments = findViewById(R.id.et_Comment);
        String str = etComments.getText().toString();
        byte[] comment = str.getBytes();

        try {
            os = new FileOutputStream(file);
            os.write(comment);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "comment saved in SD card", Toast.LENGTH_SHORT).show();
    }

    public void loadExternal(View v) {
        FileInputStream is = null;
        File file = new File(getExternalFilesDir(null), "comment2.txt");
        etComments = findViewById(R.id.et_Comment);
        try {
            is = new FileInputStream(file);
            int c;
            StringBuffer buffer = new StringBuffer();
            while ((c = is.read()) != -1) {
                buffer.append((char) c);
            }
            etComments.setText(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, getExternalFilesDir(null).toString(), Toast.LENGTH_SHORT).show();
    }
}