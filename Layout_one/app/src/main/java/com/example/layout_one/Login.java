package com.example.layout_one;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.provider.ContactsContract;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    //Variable
    ImageView image;
    TextInputLayout userName , password;
    TextView headText;
    Button login, forgot, signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        image =  findViewById(R.id.image_view);
        headText =  findViewById(R.id.logo_text);
        userName =  findViewById(R.id.username);
        password =  findViewById(R.id.password);
        login =  findViewById(R.id.go_btn);
        forgot = findViewById(R.id.forgot_btn);
        signup = findViewById(R.id.signup_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(headText,"logo_text");
                pairs[2] = new Pair<View,String>(userName,"username_tran");
                pairs[3] = new Pair<View,String>(password,"password_tran");
                pairs[4] = new Pair<View,String>(forgot,"forgot_btn_tran");
                pairs[5] = new Pair<View,String>(login,"login_btn_tran");
                pairs[6] = new Pair<View,String>(signup,"signup_btn_tran");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
                    finish();
                }
                finish();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

    }
}