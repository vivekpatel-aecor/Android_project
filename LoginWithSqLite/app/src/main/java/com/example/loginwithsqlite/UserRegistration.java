package com.example.loginwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserRegistration extends AppCompatActivity {

    private DBHelper mydb;
    private EditText username, email, password, phone, city;
    //    private String Name, Password, Email, Phone, City;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        username = findViewById(R.id.registerUserName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        city = findViewById(R.id.registerCity);
        Button register = findViewById(R.id.registerBtn);

        mydb = new DBHelper(this);


//        Name = username.getText().toString();
//        Email = email.getText().toString();
//        Password = password.getText().toString();
//        Phone = phone.getText().toString();
//        City = city.getText().toString();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( mydb.insertUserInfo(username.getText().toString(),
                        email.getText().toString(), password.getText().toString(),
                        phone.getText().toString(), city.getText().toString())) {
                    System.out.println(username.getText().toString());
                    Toast.makeText(UserRegistration.this, "Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserRegistration.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UserRegistration.this, "Not Done", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    public void moveLoginPage(View view) {
        Intent i = new Intent(UserRegistration.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}