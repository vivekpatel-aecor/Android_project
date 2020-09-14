package com.example.menuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView popUpView, optionView, contextView;
    Button floating_context, actionMode;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popUpView = (TextView) findViewById(R.id.pop_up_view);
      //  optionView = (TextView) findViewById(R.id.options_view);
        contextView = (TextView) findViewById(R.id.context_view);
        floating_context = (Button) findViewById(R.id.floating_context_button);
        actionMode = (Button) findViewById(R.id.action_mode_button);
        registerForContextMenu(contextView);


        floating_context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FloatingContextMenu.class);
                startActivity(i);
                finish();
            }
        });

        actionMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListViews.class);
                startActivity(i);
                finish();
            }
        });

        popUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenuExample();
            }
        });
    }
/////////////// pop UP MENU //////////////////////////

    private void popupMenuExample() {
        PopupMenu p = new PopupMenu(MainActivity.this, popUpView);
        p.getMenuInflater().inflate(R.menu.pop_up_menu, p .getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                title = item.getTitle().toString();
                popUpView.setText(title);
                popUpView.setTextColor(Color.parseColor(title));
                return true;
            }
        });
        p.show();
    }
/////////////////// OPTION MENU /////////////////////////////////
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

  title = item.getTitle().toString();
  optionView.setText(title);
  return true;

    }

 */

///////////////////// CONTEXT MENU //////////////////////////////


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Upload");
        menu.add(0, v.getId(), 0, "Search");
        menu.add(0, v.getId(), 0, "Share");
        menu.add(0, v.getId(), 0, "Bookmark");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        title = item.getTitle().toString();

        if (title != "Upload")
        {
            contextView.setText(title);
        }
        else if (title == "Upload")
        {
            openAlertBox();
        }
        return true;
    }

    private void openAlertBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Are you sure, You want to Upload");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        contextView.setText("Upload");
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}