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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView logo, subLogo, event, score, india, england, australia, africa, canada, bangladesh;
    ImageView logo_image, score_image, event_image, india_image, england_image, australia_image, africa_image, canada_image, bangladesh_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Hook

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        logo = findViewById(R.id.textView2);
        subLogo = findViewById(R.id.textView3);
        score = findViewById(R.id.textView4);
        event = findViewById(R.id.textView5);
        india = findViewById(R.id.textView6);
        england = findViewById(R.id.textView7);
        australia = findViewById(R.id.textView8);
        africa = findViewById(R.id.textView9);
        canada = findViewById(R.id.textView10);
        bangladesh = findViewById(R.id.textView11);

        logo_image = findViewById(R.id.imageView);
        score_image = findViewById(R.id.imageView2);
        event_image = findViewById(R.id.imageView3);
        india_image = findViewById(R.id.imageView4);
        england_image = findViewById(R.id.imageView5);
        australia_image = findViewById(R.id.imageView6);
        africa_image = findViewById(R.id.imageView7);
        canada_image = findViewById(R.id.imageView8);
        bangladesh_image = findViewById(R.id.imageView9);

        // Toolbar

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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;

            case R.id.nav_event:
                Intent intent = new Intent(Dashboard.this, Event.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_profile:
                Intent intent2 = new Intent(Dashboard.this, UserProfile.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.nav_logout:
                Intent intent3 = new Intent(Dashboard.this, Login.class);
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