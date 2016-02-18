package com.stopchildpornph.stopchildpornph;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import model.ReportUrl;

class SaveInBackground extends AsyncTask<String, Void, Void> {
    ReportUrl reportUrl = new ReportUrl();

    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            String receivedData = String.format("%s=%s&%s=%s&%s=%s",
                    URLEncoder.encode("strFullName", "UTF-8"),
                    URLEncoder.encode(reportUrl.getStrName(), "UTF-8"),
                    URLEncoder.encode("strUrlReport", "UTF-8"),
                    URLEncoder.encode(reportUrl.getStrUrl(), "UTF-8"),
                    URLEncoder.encode("strRemarks", "UTF-8"),
                    URLEncoder.encode(reportUrl.getStrRemarks(), "UTF-8"));

            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( receivedData );
            wr.flush();
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
