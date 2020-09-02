package com.example.asyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class Activity3 extends AppCompatActivity {


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Activity3.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void startAsyncTask(View view) {
        ExampleAsyncTask task = new ExampleAsyncTask(this);
        task.execute(5);
    }


    private  class ExampleAsyncTask extends AsyncTask<Integer, Integer, String>{
        private WeakReference<Activity3> activity3WeakReference;

        ExampleAsyncTask(Activity3 activity){
            activity3WeakReference = new WeakReference<Activity3>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Activity3 activity = activity3WeakReference.get();
            if (activity == null || activity.isFinishing()){
                return;
            }
            activity.progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(Integer... integers) {

            for (int i = 0; i < integers[0]; i++)
            {
                publishProgress((i*100)/integers[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finished!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Activity3 activity = activity3WeakReference.get();
            if (activity == null || activity.isFinishing()){
                return;
            }
            activity.progressBar.setProgress(values[0]);
        }

        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);
            Activity3 activity = activity3WeakReference.get();
            if (activity == null || activity.isFinishing()){
                return;
            }
            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            activity.progressBar.setProgress(0);
            activity.progressBar.setVisibility(View.INVISIBLE);
        }


    }
}