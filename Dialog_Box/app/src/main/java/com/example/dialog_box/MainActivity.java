package com.example.dialog_box;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    TextView tv1, mDisplayDate, mDisplayTime;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog.OnTimeSetListener mTimeSetListener;
    int t1Hour, t1Minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        tv1 = (TextView) findViewById(R.id.tv1);
        mDisplayDate = (TextView) findViewById(R.id.dateView);
        mDisplayTime = (TextView) findViewById(R.id.timeView);

///////////////////////////// DATE PICKER DIALOG  ///////////////////////////////////////////

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }


////////////////////////////////// ALERT DIALOG BOX ///////////////////////////////

    public void open(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to change data");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(MainActivity.this, "You clicked yes button ", Toast.LENGTH_LONG).show();
                        tv1.setText("Hi " + et1.getText().toString());

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You clicked no button", Toast.LENGTH_LONG).show();
                tv1.setText("Nothing to Show");
                //finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

/////////////////////// CUSTOM DIALOG BOX //////////////////////////////////////////////////

    public void showCustomDialog(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);

        final TextView txtView = (TextView) mView.findViewById(R.id.custom_tv1);
        final EditText txt_input = (EditText) mView.findViewById(R.id.custom_et1);
        final Button btn_ok = (Button) mView.findViewById(R.id.custom_bt1);
        final Button btn_cancel = (Button) mView.findViewById(R.id.custom_bt2);

        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked ok button ", Toast.LENGTH_LONG).show();
                tv1.setText("Hello " + txt_input.getText().toString());
                alertDialog.dismiss();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked cancel button ", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

/////////////////////// TIME PICKER DIALOG BOX ////////////////////////////////////////////

    public void showTime(View view) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(" please select the time format");

        //////////////////// 12 HOURS FORMAT /////////////////////////////////////////////////////

        alertDialogBuilder.setPositiveButton("12Hours",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1Hour, t1Minute);
                                mDisplayTime.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                        );
                        timePickerDialog.updateTime(t1Hour, t1Minute);
                        timePickerDialog.show();
                    }
                });

////////////////////////////////// 24 HOURS FORMAT ///////////////////////////////////////////////

        alertDialogBuilder.setNegativeButton("24Hours", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {

                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialogs = new TimePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener,
                        hour, minute, true);
                dialogs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogs.show();

            }
        });
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String time = hour + ":" + minute;
                mDisplayTime.setText(time);
            }
        };

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

//////////////////////////// LIST DIALOG BOX //////////////////////////////////////////////////

    public void showListDialog(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final CharSequence[] items = {"Red", "Green", "Blue"};

        builder.setTitle("Chose Color..");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case 0:
                        tv1.setTextColor(Color.RED);
                        tv1.setText(items[0]);
                        break;
                    case 1:
                        tv1.setTextColor(Color.GREEN);
                        tv1.setText(items[1]);
                        break;
                    case 2:
                        tv1.setTextColor(Color.BLUE);
                        tv1.setText(items[2]);
                        break;
                }
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

////////////////////////////// FRAGMENT DIALOG BOX /////////////////////////////////////////////

    public void changeActivity(View view) {
        Intent intent = new Intent(MainActivity.this, FragmentDialog.class);
        startActivity(intent);
        finish();
    }

  /*  public void showDate(View view) {
    mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

*/

///////////////////////// INTENT DATA PASSING PAGE //////////////////////////////////////////

    public void intentDataPage(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
        finish();
    }
}