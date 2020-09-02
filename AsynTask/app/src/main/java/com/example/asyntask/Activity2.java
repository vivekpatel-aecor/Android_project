package com.example.asyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;

public class Activity2 extends AppCompatActivity {

    String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";
    String title;
    TextView titleTextView, bodyTextView;
    ProgressDialog progressDialog;
    Button displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
    //    bodyTextView = (TextView) findViewById(R.id.bodyTextView);
        displayData = (Button) findViewById(R.id.displayData);

        displayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new  Intent(Activity2.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Activity2.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
        }
////////////////////////////////////////////////////////////////////////////////////////////////////
        @Override
        protected String doInBackground(String... params) {
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiUrl);

                    urlConnection  = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                       // System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }
///////////////////////////////////////////////////////////////////////////////////
        @Override
        protected void onPostExecute(String current) {

           // Log.d("data", s.toString());
            progressDialog.dismiss();
            try {
                title = current.toString();
                titleTextView.setText("Data: "+title);

            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                JSONArray jsonArray = new JSONArray(current);
//
//                JSONObject oneObject = jsonArray.getJSONObject(0);
//                title = oneObject.getString("userId");
//                //body = oneObject.getString("body");
//                //title = current.toString();
//                titleTextView.setText("Data: "+title);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }
}