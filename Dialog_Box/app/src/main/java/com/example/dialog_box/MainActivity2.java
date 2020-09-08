package com.example.dialog_box;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    private TextView nameTxt,yearTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nameTxt= (TextView) findViewById(R.id.nameTxt);
        yearTxt= (TextView) findViewById(R.id.yearTxt);

        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
         if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("SENDER_KEY"))
         {
             final String sender = this.getIntent().getExtras().getString("SENDER_KEY");

             if(sender != null)
             {
                 this.receiveData();
                 Toast.makeText(this, "Received", Toast.LENGTH_SHORT).show();
             }
         }
    }


    private void openFragment()
    {
        MyFragment myFragment = new MyFragment();
        //THEN NOW SHOW OUR FRAGMENT
        getSupportFragmentManager().beginTransaction().replace(R.id.container,myFragment).commit();
    }


    private void receiveData()
    {
        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        String name = i.getStringExtra("NAME_KEY");
        int year = i.getIntExtra("YEAR_KEY",0);
        //SET DATA TO TEXTVIEWS
        nameTxt.setText(name);
        yearTxt.setText(String.valueOf(year));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}