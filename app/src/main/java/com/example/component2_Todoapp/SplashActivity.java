package com.example.component2_Todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


import com.example.component2_Todoapp.tasks.ActivityLogin;
import com.example.component2_Todoapp.tasks.MainActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                Boolean authentication = preferences.getBoolean("authentication", false);
                if(authentication){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class
                    );
                    startActivity(intent);
                }else{
                    Intent intent =  new Intent(SplashActivity.this, ActivityLogin.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 4000);
    }
}