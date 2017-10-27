package com.example.lopez_labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    Button btnLoadSharedPreferences, btnLoadInternalStorage, btnClear, btnBack;
    TextView tvDisplay;
    FileInputStream fis;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnLoadSharedPreferences = (Button) findViewById(R.id.btn_loadSharedPreferences);
        btnLoadInternalStorage = (Button) findViewById(R.id.btn_loadInternalStorage);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnBack = (Button) findViewById(R.id.btn_back);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        preferences = getSharedPreferences("pref",Context.MODE_WORLD_READABLE);
    }

    public void loadSharedPreferences (View view) {
        String username = preferences.getString("user", "");
        String password = preferences.getString("pwd", "");

        tvDisplay.setText("The user is " + username + " and the password is " + password );
    }

    public void loadInternalStorage (View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            } fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void clearText (View view) {
        tvDisplay.setText("");
    }

    public void goBack (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
