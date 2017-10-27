package com.example.lopez_labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnSharedPreferences, btnInternalStorage, btnNext;
    SharedPreferences preferences;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnSharedPreferences = (Button) findViewById(R.id.btn_sharedPreferences);
        btnInternalStorage = (Button) findViewById(R.id.btn_internalStorage);
        btnNext = (Button) findViewById(R.id.btn_next);
        preferences = getSharedPreferences("pref", Context.MODE_WORLD_READABLE);
    }

    public void saveSharedPreference (View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", etUsername.getText().toString());
        editor.putString("pwd", etPassword.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalStorage (View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String message1 = "Username is ";
        String message2 = " and password is ";
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message1.getBytes());
            fos.write(username.getBytes());
            fos.write(message2.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_SHORT).show();
    }

    public void goToNext (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
