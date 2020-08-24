package com.example.life_cycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,"Create",Toast.LENGTH_SHORT).show();
        Log.d(Tag,"on create");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(MainActivity.this,"Start",Toast.LENGTH_SHORT).show();
        Log.d(Tag,"Start");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(MainActivity.this,"Resume",Toast.LENGTH_SHORT).show();
        Log.d(Tag,"Resume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(MainActivity.this,"Pause",Toast.LENGTH_SHORT).show();
        Log.d(Tag,"Pause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(MainActivity.this,"Stop",Toast.LENGTH_SHORT).show();
        Log.d(Tag,"Stop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(MainActivity.this,"Destroy",Toast.LENGTH_SHORT).show();
        Log.d(Tag,"Destroy");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(MainActivity.this,"Restart",Toast.LENGTH_SHORT).show();
        Log.d(Tag,"Restart");
    }


    public void openActivity3(View view) {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
        finish();
    }

    public void StartService(View view) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("Score","10");
        startService(intent);
    }

    public void StopService(View view) {
        Intent intent = new Intent(this, Activity2.class);
        stopService(intent);
    }

    public void StartIntentService(View view) {
        Intent i = new Intent(this, IntentServices.class);
        startService(i);
    }

    public void StopIntentService(View view) {
        Intent i = new Intent(this, IntentServices.class);
        stopService(i);
    }
}