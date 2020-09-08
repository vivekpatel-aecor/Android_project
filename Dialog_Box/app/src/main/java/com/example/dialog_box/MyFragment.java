package com.example.dialog_box;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MyFragment extends Fragment {

    private EditText nameFragTxt;
    private Spinner launchYearSpinner;
    private Button sendBtn;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_my, container, false);
        //INITIALIZE VIEWS
        nameFragTxt = (EditText)rootView.findViewById(R.id.nameEditTxt);
        launchYearSpinner = (Spinner)rootView.findViewById(R.id.sp);
        sendBtn = (Button) rootView.findViewById(R.id.sendBtn);
        fillYears();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
        return rootView;
    }

    private void fillYears() {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1);
        adapter.add("2017");
        adapter.add("2018");
        adapter.add("2019");
        adapter.add("2020");
        adapter.add("2021");
        adapter.add("2022");
        //SET ADAPTER INSTANCE TO OUR SPINNER
        launchYearSpinner.setAdapter(adapter);
    }
    private void sendData()
    {

        Intent i = new Intent(getActivity().getBaseContext(),
                MainActivity2.class);
        //PACK DATA
        i.putExtra("SENDER_KEY", "MyFragment");
        i.putExtra("NAME_KEY", nameFragTxt.getText().toString());
        i.putExtra("YEAR_KEY", Integer.valueOf(launchYearSpinner.getSelectedItem().toString()));
        nameFragTxt.setText("");
        launchYearSpinner.setSelection(0);
        //START ACTIVITY
        getActivity().startActivity(i);
    }
}