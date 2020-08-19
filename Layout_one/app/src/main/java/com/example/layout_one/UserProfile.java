package com.example.layout_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class UserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variable
    Button upadte;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hock
        upadte = findViewById(R.id.update_btn);
        drawerLayout = findViewById(R.id.drawer_layout_profile);
        navigationView = findViewById(R.id.nav_view_profile);
        toolbar = findViewById(R.id.profile_menu);

        setSupportActionBar(toolbar);

        //Navigation Drawer Menu

        // Hide or Show Items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        upadte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

    }

        @Override
        public void onBackPressed() {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else {
                super.onBackPressed();
            }
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
        switch (menuitem.getItemId()){
            case R.id.nav_home:
                Intent i_home = new Intent(UserProfile.this, Dashboard.class);
                startActivity(i_home);
                finish();
                break;

            case R.id.nav_event:
                Intent intent = new Intent(UserProfile.this, Event.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_profile:
                Intent intent2 = new Intent(UserProfile.this, UserProfile.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.nav_logout:
                Intent intent3 = new Intent(UserProfile.this, Login.class);
                startActivity(intent3);
                finish();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
