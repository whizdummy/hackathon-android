package com.stopchildpornph.stopchildpornph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import model.ReportSms;

public class ReportUsingSMS extends AppCompatActivity {

    private EditText                    editTextName;
    private EditText                    editTextReportInfo;
    private Button                      btnReport;
    private ReportSms                   report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_using_sms);
        setUp();
    }

    private void setUp(){

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextReportInfo = (EditText)findViewById(R.id.editTextReportInfo);
        btnReport = (Button)findViewById(R.id.buttonReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void getData(){

        report = new ReportSms();
        report.setStrName(editTextName.getText().toString());
        report.setStrReport(editTextReportInfo.getText().toString());

    }

}
