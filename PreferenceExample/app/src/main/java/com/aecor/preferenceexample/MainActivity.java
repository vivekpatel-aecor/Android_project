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
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvPrefs)
    protected TextView tvPrefs;

    protected  String userName, password, listPrefs, builder;
    protected boolean checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnStoreInformation)
    protected void onStoreInformationClicked (){
        Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                        startActivity(intent);
    }
    @OnClick(R.id.btnShowInformation)
    protected void onShowInformationClicked (){
        displaySharedPreferences();
    }

    private void displaySharedPreferences() {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        userName = preference.getString("username", "Default NickName");
        password = preference.getString("password", "Default Password");
        checkBox = preference.getBoolean("checkBox", false);
        listPrefs = preference.getString("listPref", "Default list prefs");


        builder = "Username:  " + userName + "\n" +
                "Password:  " + password + "\n" +
                "Keep me logged in:  " + String.valueOf(checkBox) + "\n" +
                "List preference:  " + listPrefs;
        tvPrefs.setText(builder);

    }
}
