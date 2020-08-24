package com.example.life_cycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.security.Provider;

import static android.content.ContentValues.TAG;

public class Activity2 extends Service {

    private static int score = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(Activity2.this,"Service Create",Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: Service Create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(Activity2.this,"Service Destroy",Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy: Service Destroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        score += Integer.parseInt(intent.getStringExtra("Score"));
        Toast.makeText(Activity2.this,"Service Running, score : "+ String.valueOf(score),Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStartCommand: Service Running, score : "+ String.valueOf(score));
        return super.onStartCommand(intent, flags, startId);
    }

}