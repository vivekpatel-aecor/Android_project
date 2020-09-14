package com.example.menuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FloatingContextMenu extends AppCompatActivity {
    Context mContext;
    Activity mActivity;
    RelativeLayout mRelativeLayout;
    Toolbar mToolbar;
    TextView floting_Context_View, group_menu_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_context_menu);

        floting_Context_View = (TextView) findViewById(R.id.floating_context_view);
        registerForContextMenu(floting_Context_View);

        mContext = getApplicationContext();
        mActivity = FloatingContextMenu.this;

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mToolbar  =(Toolbar) findViewById(R.id.toolbar);
        group_menu_view = (TextView) findViewById(R.id.group_menu_view);

        mToolbar.setTitle("Group Menu");
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
    }
//////////////////////// FLOATING CONTEXT MENU //////////////////////
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        floting_Context_View.setText(title);

        switch (item.getItemId()){
            case R.id.edit_item:
                floting_Context_View.setTextColor(Color.BLUE);
                return true;
            case R.id.delete_item:
                floting_Context_View.setTextColor(Color.RED);
                return true;
            default:
                floting_Context_View.setTextColor(Color.GRAY);
                return true;
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(FloatingContextMenu.this, MainActivity.class);
        startActivity(i);
        finish();
    }

///////////////////  TOOLBAR GROUP MENU  ////////////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.text_bold:
                group_menu_view.setTypeface(null, Typeface.BOLD);
                return true;
            case R.id.text_normal:
                group_menu_view.setTypeface(null, Typeface.NORMAL);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

public void onColorGroupItemClick(MenuItem item){

/*
        switch (item.getItemId()){
            case R.id.red:
                if (item.isChecked())group_menu_view.setTextColor(Color.RED);
                break;
            case R.id.green:
                if (item.isChecked()) group_menu_view.setTextColor(Color.GREEN);
                else item.setChecked(true);
                break;
            case R.id.blue:
                if (item.isChecked()) group_menu_view.setTextColor(Color.BLUE);
                else item.setChecked(true);
                break;
            default:
                return;
        }


 */

    if(item.getItemId() == R.id.red){
        item.setChecked(true);
        group_menu_view.setTextColor(Color.RED);
    }else if (item.getItemId() == R.id.green){
        item.setChecked(true);
        group_menu_view.setTextColor(Color.GREEN);
    }else if (item.getItemId() == R.id.blue){
        item.setChecked(true);
        group_menu_view.setTextColor(Color.BLUE);
    }else {
        // Do nothing
    }
}

    // To handle menu text size group items click event
    public void onSizeGroupItemClick(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.large_text){
            item.setChecked(true);
            group_menu_view.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50);
        }else if (id == R.id.medium_text){
            item.setChecked(true);
            group_menu_view.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
        }else if (id == R.id.small_text){
            item.setChecked(true);
            group_menu_view.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        }else {
            // Do nothing
        }
    }
}