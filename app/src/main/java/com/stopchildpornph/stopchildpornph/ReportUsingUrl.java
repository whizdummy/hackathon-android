package com.stopchildpornph.stopchildpornph;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ReportUsingUrl extends AppCompatActivity {
    EditText editTxtName;
    private static final String REPORT_URL = "http://hk4.dswd.gov.ph/test-connection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_using_url);
    }

    public void onClick(View v) {
        editTxtName = (EditText) findViewById(R.id.editTxtName);
    }

    class Background extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }
}
