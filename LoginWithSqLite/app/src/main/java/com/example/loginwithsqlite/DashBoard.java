package com.example.loginwithsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {

    EditText uName, uPassword, uEmail, uCity, uPhone;
    DBHelper mydb;
    Button delete, update, show;
    String name , email, password, phone, city, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        uName = findViewById(R.id.dashUserName);
        uEmail = findViewById(R.id.dashEmail);
        uPassword = findViewById(R.id.dashPassword);
        uCity = findViewById(R.id.dashCity);
        uPhone = findViewById(R.id.dashPhone);
        delete = findViewById(R.id.deleteUser);
        update = findViewById(R.id.updateUser);
        show = findViewById(R.id.readUser);

        mydb = new DBHelper(this);


    }


    @Override
    public void onBackPressed() {
    }

    public void showUserInfo(View view) {
        delete.setVisibility(View.VISIBLE);
        update.setVisibility(View.VISIBLE);

        Bundle info = getIntent().getExtras();
        if (info != null) {
            String value = info.getString("email");
            System.out.println("Dashboard email :"+value);
            if (value != null) {

            //    uEmail.setText(value);

                Cursor rs = mydb.getUserInfo(value);
                rs.moveToFirst();

                id = rs.getString(rs.getColumnIndex(DBHelper.USER_COLUMN_ID));
                name = rs.getString(rs.getColumnIndex(DBHelper.USER_COLUMN_USERNAME));
                email = rs.getString(rs.getColumnIndex(DBHelper.USER_COLUMN_EMAIL));
                password = rs.getString(rs.getColumnIndex(DBHelper.USER_COLUMN_PASSWORD));
                phone = rs.getString(rs.getColumnIndex(DBHelper.USER_COLUMN_PHONE));
                city = rs.getString(rs.getColumnIndex(DBHelper.USER_COLUMN_CITY));

                if (!rs.isClosed()) {
                    rs.close();
                }
                System.out.println("Name :"+name);
                System.out.println("Email :"+email);
                System.out.println("Password :"+password);
                System.out.println("Phone :"+phone);
                System.out.println("City :"+city);

                uName.setText((CharSequence)name);
                uEmail.setText((CharSequence)email);
                uPhone.setText((CharSequence)phone);
                uPassword.setText((CharSequence)password);
                uCity.setText((CharSequence)city);

            }
        }
    }

    public void logoutUser(View view) {
        Intent intent = new Intent(DashBoard.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void deleteUser(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteUser)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mydb.deleteUserInfo(uEmail.getText().toString());
                        Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        AlertDialog d = builder.create();
        d.setTitle("Are you sure");
        d.show();
    }

    public void updateUserInfo(View view) {
       if (mydb.updateUserInfo(uName.getText().toString(),
               uEmail.getText().toString(), uPassword.getText().toString(),
               uPhone.getText().toString(), uCity.getText().toString())){
           Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(getApplicationContext(), "Update failed", Toast.LENGTH_SHORT).show();
       }


    }

}