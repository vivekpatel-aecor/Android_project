package com.example.loginwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText email, password;
    DBHelper mydb;
    String Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email =  findViewById(R.id.etUserName);
        password =  findViewById(R.id.etPassword);
        login =  findViewById(R.id.loginBtn);

        Email = email.getText().toString();
        Password = password.getText().toString().trim();
        mydb = new DBHelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean success = mydb.login(email.getText().toString(), password.getText().toString());
                 if (success) {
                     Bundle dataBundle = new Bundle();
                     dataBundle.putString("email",email.getText().toString());
                     Intent i = new Intent(MainActivity.this, DashBoard.class);
                     System.out.println(dataBundle);
                    i.putExtras(dataBundle);
                    startActivity(i);
                }
                else {
                    password.setText(null);
                    Toast.makeText(MainActivity.this,"failedLogin",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createNewAccount(View view) {
        Intent intent = new Intent(MainActivity.this, UserRegistration.class);
        startActivity(intent);
        fileList();
    }
}