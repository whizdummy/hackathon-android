package com.stopchildpornph.stopchildpornph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                String strPhoneNo = "+18044913997";
                String strMessage = report.getStrName()+"//"+report.getStrReport();

                try{

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(strPhoneNo, null, strMessage, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

    }

    private void getData(){

        report = new ReportSms();
        report.setStrName(editTextName.getText().toString());
        report.setStrReport(editTextReportInfo.getText().toString());

    }



}
