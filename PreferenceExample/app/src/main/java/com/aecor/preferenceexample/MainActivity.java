package com.aecor.preferenceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnStoreInformation) Button btnStoreInformation;
    @BindView(R.id.btnShowInformation) Button btnShowInformation;
    @BindView(R.id.tvPrefs) TextView tvPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    //    initView();
        setListener();
    }

//    protected void initView() {
//
//    }

    protected void setListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnStoreInformation:
                        Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnShowInformation:
                        displaySharedPreferences();
                        break;
                    default:
                        break;
                }
            }
        };
        btnStoreInformation.setOnClickListener(listener);
        btnShowInformation.setOnClickListener(listener);
    }

    private void displaySharedPreferences() {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = preference.getString("username", "Default NickName");
        String password = preference.getString("password", "Default Password");
        boolean checkBox = preference.getBoolean("checkBox", false);
        String listPrefs = preference.getString("listPref", "Default list prefs");


        String builder = "Username:  " + username + "\n" +
                "Password:  " + password + "\n" +
                "Keep me logged in:  " + String.valueOf(checkBox) + "\n" +
                "List preference:  " + listPrefs;
        tvPrefs.setText(builder);

    }
}
