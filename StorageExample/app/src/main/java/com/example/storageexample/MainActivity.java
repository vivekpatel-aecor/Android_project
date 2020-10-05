package com.example.storageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTitle)
    protected EditText etTitle;
    @BindView(R.id.tvTitle)
    protected TextView tvTitle;
    @BindView(R.id.tvData)
    protected TextView tvData;
    @BindView(R.id.etData)
    protected EditText etData;

    protected StringBuffer stringBuffer;
    protected String fileName, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnInsert)
    protected void insertData() {
        fileName = etTitle.getText().toString();
        data = etData.getText().toString();
        System.out.println("File Name: " + fileName);
        System.out.println("Data: " + data);
        FileOutputStream fos;
        try {
            System.out.println(fileName);
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(), fileName + "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btnShow)
    protected void showData() {
        fileName = etTitle.getText().toString();
        stringBuffer = new StringBuffer();
        try {
            System.out.println(fileName);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            String inputString;
            while ((inputString = br.readLine()) != null) {
                stringBuffer.append(inputString).append("\n");
            }
            etData.setText(stringBuffer);
            Toast.makeText(getApplicationContext(), stringBuffer+"Show data successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnClick(R.id.btnImagePage)
    protected void moveToImagePage (View view){
        Intent intent = new Intent(MainActivity.this, ImageSaveActivity.class);
        startActivity(intent);
    }
}