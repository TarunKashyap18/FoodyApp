package com.example.tkashyap.foody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        rg = (RadioGroup) findViewById(R.id.rg);
    }

    public void thanksmsg(View view) {
        int type = rg.getCheckedRadioButtonId();
        if(type>0){
            Toast.makeText(this,"Successfully Ordered",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else
            Toast.makeText(this,"Please select payment option",Toast.LENGTH_SHORT).show();
    }
}
