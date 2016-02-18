package com.stopchildpornph.stopchildpornph;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CallRedict extends AppCompatActivity {

    private Button                  buttonDSWD;
    private Button                  buttonPNP;
    private String                  strPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_redict);
    }

    private void setUp(){

        buttonDSWD = (Button) findViewById(R.id.buttonDSWD);
        buttonPNP = (Button) findViewById(R.id.buttonPNP);
        buttonDSWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strPhone = "+639753664645";
                call();
            }
        });
        buttonPNP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strPhone = "+639261570334";
            }
        });

    }

    private void call(){

        Intent in=new Intent(Intent.ACTION_CALL, Uri.parse(strPhone));
        try{
            startActivity(in);
        }

        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
        }

    }

}
