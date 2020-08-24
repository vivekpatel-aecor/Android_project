package com.example.life_cycle;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class IntentServices extends IntentService {
    public  IntentServices(){
        super("intent_service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Intent Service Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: Intent Service Created");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this,"Intent Service Running", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStartCommand: Intent Service Running");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Toast.makeText(this,"Intent Service OnHandleIntent", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onHandleIntent: Intent Service OnHandleIntent");
        return;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Intent Service Stopped",Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy: Intent Service Stopped");
        super.onDestroy();

    }
}
