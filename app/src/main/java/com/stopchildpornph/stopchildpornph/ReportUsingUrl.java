package com.stopchildpornph.stopchildpornph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ReportUrl;

public class ReportUsingUrl extends AppCompatActivity {

    private EditText                            editTextName;
    private EditText                            editTextUrl;
    private EditText                            editTextRemarks;
    private Button                              btnReport;
    private ReportUrl                           report;
    private boolean                             boolValidation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_using_url);
        setUp();
    }

    private void setUp(){

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextUrl = (EditText)findViewById(R.id.editTextUrl);
        editTextRemarks = (EditText)findViewById(R.id.editTextRemarks);
        btnReport = (Button)findViewById(R.id.buttonReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                validation();
                if (!boolValidation){

                    //saving database

                }else{
                    //display error
                }
            }
        });

    }

    private void validation(){

        String strPattern = "<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>";
        if (report.getStrName().length() == 0 || report.getStrName() == null){
            boolValidation = true;
        }
        if (report.getStrRemarks().length() == 0 || report.getStrRemarks() == null){
            boolValidation = true;
        }
        if (report.getStrUrl().length() == 0 || report.getStrUrl() == null){
            boolValidation = true;
        }
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(report.getStrUrl());
        if (!matcher.matches()){
            boolValidation = true;
        }

    }

    public void getData(){

        report = new ReportUrl();
        report.setStrName(editTextName.getText().toString());
        report.setStrUrl(editTextUrl.getText().toString());
        report.setStrRemarks(editTextRemarks.getText().toString());

    }

}
