package com.example.tkashyap.foody;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CheckBox dessert, drink, snack;
    RadioButton rb1 ,rb2,rb3;
    RadioGroup rg;
    Spinner spin;
    Button order,cart;
    TextView tv,tvResult;
    int totalAmount=0;
    String str ;
    String selected;
    int c=0;
    ArrayList<String> item = new ArrayList<String>();
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
//        list=null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spin = (Spinner) findViewById(R.id.spinner);
        addingListeners();
        disableItem();
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(str == null )
                    Toast.makeText(getApplicationContext(),"\tYou have not selected any item yet ",Toast.LENGTH_SHORT).show();
                else {
                    tvResult.setText("\tSelected Item's list \n" + str);
                }
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(str.equals(null) || item.equals( null) )
                    Toast.makeText(getApplicationContext(),"You have not selected any item yet ",Toast.LENGTH_SHORT).show();
                else if(c<1) {
                    if(totalAmount != 0){
                        String result = "\tTotal amount to be paid : "+totalAmount;
                        item.add(result);
//                        ArrayAdapter listItem = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,item);
                        c++;
                        Intent i = new Intent(getApplicationContext(),order.class);
                        i.putStringArrayListExtra("item",item);
                        startActivity(i);
                        item.clear();
                    }
                }
            }
        });
    }

    private void disableItem() {
        spin.setVisibility(View.GONE);
        rb1.setVisibility(View.GONE);
        rb2.setVisibility(View.GONE);
        rb3.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
    }

    public void addingListeners() {
        drink = (CheckBox) findViewById(R.id.checkBox);
        dessert = (CheckBox) findViewById(R.id.checkBox2);
        snack = (CheckBox) findViewById(R.id.checkBox3);
        rb1 =(RadioButton) findViewById(R.id.radioButton1);
        rb2 =(RadioButton) findViewById(R.id.radioButton2);
        rb3 =(RadioButton) findViewById(R.id.radioButton3);
        rg =(RadioGroup) findViewById(R.id.radioGroup);
        tv = (TextView) findViewById(R.id.textView);
        tvResult = (TextView) findViewById(R.id.textView2);
        order = (Button) findViewById(R.id.button3);
        cart = (Button) findViewById(R.id.button);
    }
    public void setVisible(){
        rb1.setVisibility(View.VISIBLE);
        rb2.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
    }

    public void resetRadioButton(){
        rg.clearCheck();
    }

    public void addFunctionality(View view) {
        if(drink.isChecked()) {
            spin.setVisibility(View.VISIBLE);
            setVisible();
            String snk = Arrays.toString(getResources().getStringArray(R.array.drinks));
            Toast.makeText(getApplicationContext(),snk,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Please select the quantity first",Toast.LENGTH_SHORT).show();
            if(drink.isChecked()&&(snack.isChecked()||dessert.isChecked())){
                if(snack.isChecked())
                    snack.toggle();
                else if(dessert.isChecked())
                    dessert.toggle();
                else {
                    dessert.toggle();
                    snack.toggle();
                }
            }
            resetRadioButton();
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.drinks,R.layout.custom_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected  = adapterView.getItemAtPosition(i).toString();
                    if(selected.equals("Apple Cider")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected );
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected );
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add( "\t" + 3 + selected );
                        }
                    }
                    if(selected.equals("Peanut Punch")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected.equals("Shikanji")) {
                        setVisible();

                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected .equals("Ginger Tea")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(str == ""){
//                        tvResult.setText("Error in adding Item in List");
                    }
//                    else
//                    Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        if(dessert.isChecked()) {
            spin.setVisibility(View.VISIBLE);
            setVisible();
            String snk = Arrays.toString(getResources().getStringArray(R.array.desserts));
            Toast.makeText(getApplicationContext(),snk,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Please select the quantity first",Toast.LENGTH_SHORT).show();
            if(dessert.isChecked()&&(drink.isChecked()||snack.isChecked())){
                if(drink.isChecked())
                    drink.toggle();
                else if(snack.isChecked())
                    snack.toggle();
                else {
                    snack.toggle();
                    drink.toggle();
                }
            }

            resetRadioButton();
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.desserts, R.layout.custom_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected  = adapterView.getItemAtPosition(i).toString();
                    if(selected.equals("Gulab Jamun")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected.equals("Rasgulla")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected.equals("Pinni")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {

                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected .equals("Bal Mithai")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
//                    if(str == null){
//                        tvResult.setText("Error in adding Item in List");
//                    }
//                    else
//                    Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

        }
        if(snack.isChecked()) {
            spin.setVisibility(View.VISIBLE);
            setVisible();
            String snk = Arrays.toString(getResources().getStringArray(R.array.snacks));
            Toast.makeText(getApplicationContext(),snk,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Please select the quantity first",Toast.LENGTH_SHORT).show();

            if(snack.isChecked()&&(drink.isChecked()||dessert.isChecked())){
                if(drink.isChecked())
                    drink.toggle();
                else if(dessert.isChecked())
                    dessert.toggle();
                else {
                    dessert.toggle();
                    drink.toggle();
                }
            }
            resetRadioButton();
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.snacks, R.layout.custom_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected  = adapterView.getItemAtPosition(i).toString();
                    if(selected.equals("Bhelpuri")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected.equals("Bonda")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected.equals("Dabeli")) {
                        setVisible();

                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
                    if(selected .equals("Dhokla")) {
                        setVisible();
                        int q = rg.getCheckedRadioButtonId();
                        if(str == null){
                            str="";
                        }
                        if (R.id.radioButton1 == q) {
                            str += "\t" + selected + " -> " + 1;
                            totalAmount += 10;
                            item.add("\t" + 1 + selected);
                        }
                        if (R.id.radioButton2 == q) {
                            str += "\t" + selected + " -> " + 2;
                            totalAmount += 20;
                            item.add("\t" + 2 + selected);
                        }
                        if (R.id.radioButton3 == q) {
                            str += "\t" + selected + " -> " + 3;
                            totalAmount += 30;
                            item.add("\t" + 3 + selected);
                        }
                    }
//                    if(str == null){
//                        tvResult.setText("Error in adding Item in List");
//                    }
//                    else
////                        list.append(str);
//                    Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
    }

    public void clear(View view) {
        str="";
        Toast.makeText(getApplicationContext(),"List is Emapty Now",Toast.LENGTH_SHORT).show();
        tvResult.setText("");
        resetRadioButton();
        totalAmount=0;
        c=0;

        //item.clear();
        disableItem();
        drink.setChecked(false);
        snack.setChecked(false);
        dessert.setChecked(false);
    }
}
