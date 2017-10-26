package com.example.tkashyap.foody;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;

public class order extends AppCompatActivity {
    ListView lv;
    TextView tv;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                return true;
            case R.id.item2:
                // Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                Intent i1 = new Intent(this,About.class);
                startActivity(i1);
                return true;
            case R.id.item3:
                //Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to Exit ?")
                        .setTitle("Exit")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        lv = (ListView) findViewById(R.id.lv1);
        tv = (TextView) findViewById(R.id.textView);

        Bundle b=this.getIntent().getExtras();
        String[] selectedA=b.getStringArray("item");
        String[] priceA=b.getStringArray("price");
        String[] totalAmountA=b.getStringArray("totalAmount");
        String[] quantityA=b.getStringArray("quantity");
        String tp =this.getIntent().getExtras().getString("tp");
        tv.setText("Total Amount to be paid : "+tp);
        MyAdapter adapter = new MyAdapter(order.this,selectedA,priceA,totalAmountA,quantityA);
        lv.setAdapter(adapter);
    }

    public void payment(View view) {
        Intent i = new Intent(this,Payment.class);
        startActivity(i);
    }
}
