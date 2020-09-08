package com.example.intent_data_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText title,data,number;
    TextView show_date;
    String  Title, Data;
    int Number;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (EditText) findViewById(R.id.etTitle);
        data = (EditText) findViewById(R.id.etData);
        number = (EditText) findViewById(R.id.etInt);
        send = (Button) findViewById(R.id.btnSubmit);
        show_date = (TextView) findViewById(R.id.tv2);

        Intent i = getIntent();
        String date = i.getStringExtra("Date");
        show_date.setText(date);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Title = title.getText().toString();
                Data = data.getText().toString();
                Number = Integer.parseInt(number.getText().toString());

                intent.putExtra("Title",Title);
                intent.putExtra("Data",Data);

                Bundle bundle = new Bundle();
                bundle.putInt("Number",Number);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });
    }
}