package com.example.dialog_box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FragmentDialog extends AppCompatActivity implements DialogCustom2.OnInputListener{

    private static final String TAG = "FragmentDialog";

    @Override
    public void sendInput(String input) {
//        mInputDisplay.setText(input);
        mInput = input;
        setInputToTextView();
    }

    private Button mOpenDialog;
    public TextView mInputDisplay;

    //vars
    public String mInput;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dialog);

        mOpenDialog = findViewById(R.id.open_dialog);
        mInputDisplay = findViewById(R.id.input_display);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogCustom2 dialog = new DialogCustom2();
                dialog.show(getSupportFragmentManager(),"Dialog");

            }
        });

    }


    private void setInputToTextView(){
        mInputDisplay.setText(mInput);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FragmentDialog.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}