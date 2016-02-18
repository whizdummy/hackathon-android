package com.stopchildpornph.stopchildpornph;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import model.ReportUrl;

public class ReportUsingUrl extends AppCompatActivity {
    EditText editTxtName;
    private static final String REPORT_URL = "http://hk4.dswd.gov.ph/test-connection";

    private EditText                            editTextName;
    private EditText                            editTextUrl;
    private EditText                            editTextRemarks;
    private Button                              btnReport;
    private ReportUrl                           report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_using_url);
        setUp();
    }

    private void setUp(){

        editTextName = (EditText)findViewById(R.id.editTxtName);
        editTextUrl = (EditText)findViewById(R.id.editTextUrl);
        editTextRemarks = (EditText)findViewById(R.id.editTextRemarks);
        btnReport = (Button)findViewById(R.id.buttonReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                (new SaveInBackground()).execute(REPORT_URL);
            }
        });

    }

    public void getData(){

        report = new ReportUrl();
        report.setStrName(editTextName.getText().toString());
        Log.e("NAME", editTextName.getText().toString());
        report.setStrUrl(editTextUrl.getText().toString());
        report.setStrRemarks(editTextRemarks.getText().toString());

    }

    class SaveInBackground extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                Log.e("URL", url.toString());
                Log.e("NAME GET", report.getStrName());
                String receivedData = String.format("%s=%s&%s=%s&%s=%s",
                        URLEncoder.encode("strFullName", "UTF-8"),
                        URLEncoder.encode(report.getStrName(), "UTF-8"),
                        URLEncoder.encode("strUrlReport", "UTF-8"),
                        URLEncoder.encode(report.getStrUrl(), "UTF-8"),
                        URLEncoder.encode("strRemarks", "UTF-8"),
                        URLEncoder.encode(report.getStrRemarks(), "UTF-8"));
//                StringBuffer sbData = new StringBuffer();
//                sbData.append(URLEncoder.encode("strFullName", "UTF-8"));
//                sbData.append("=");
//                sbData.append(URLEncoder.encode(report))

                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(receivedData);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sbData = new StringBuilder();
                String line = null;

                while((line = reader.readLine()) != null){
                    sbData.append(line);
                    break;
                }
                return sbData.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
