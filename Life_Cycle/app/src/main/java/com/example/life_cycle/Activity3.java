package com.example.life_cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3 extends AppCompatActivity {

    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        back = findViewById(R.id.btnreturn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(Activity3.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent  intent = new Intent(Activity3.this,MainActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }
}