package com.example.tkashyap.foody;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CheckBox dessert, drink, snack;
    Spinner spin,spin2;
    Button order,cart;
    ListView lv;
    TextView tv,tvResult;
    int totalAmount=0;
    String str ;
    String selected;
    String  quantity = "0";
    int price = 0;
    int c=0,flag=0;
    int p=1,tp=0;

    final List<String> selectedItemL = new ArrayList<String>();
    final List<String> priceL = new ArrayList<String>();
    final List<String> totalAmountL = new ArrayList<String>();
    final List<String> quantityL = new ArrayList<String>();

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
//        price=totalAmount=0;
//        quantity = "0";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spin = (Spinner) findViewById(R.id.spinner);
        spin2 = (Spinner) findViewById(R.id.spinner2);
        addingListeners();
        disableItem();
            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(flag>0) {
                        c = 0;
                        if (!selected.equals("Select Items") && Integer.parseInt(quantity) != 0) {
                            selectedItemL.add(selected);
                            quantityL.add(quantity);
                            priceL.add(String.valueOf(price));
                            totalAmountL.add(String.valueOf(totalAmount));
                            tp += Integer.parseInt(quantity) * price;
                            String result = tvResult.getText() + "\nItem : " + selected + " " + " Quantity : " + quantity;
                            tvResult.setText(result);
                        }
                        if (selected.equals("Select Items"))
                            Toast.makeText(getApplicationContext(), "You have not selected any item yet ", Toast.LENGTH_SHORT).show();
                        if (Integer.parseInt(quantity) == 0)
                            if(!selected.equals("Select Items"))
                                Toast.makeText(getApplicationContext(), "Please Select the quantity of " + selected, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(flag>0) {
                        if (selected.equals("Select Items") || Integer.parseInt(quantity) == 0)
                            Toast.makeText(getApplicationContext(), "You have not selected any item yet ", Toast.LENGTH_SHORT).show();
                        if (c < 1) {
                            if (tp != 0) {
                                c++;
                                String[] selectedA = new String[selectedItemL.size()];
                                String[] priceA = new String[priceL.size()];
                                String[] totalAmountA = new String[totalAmountL.size()];
                                String[] quantityA = new String[quantityL.size()];
                                selectedA = selectedItemL.toArray(selectedA);
                                priceA = priceL.toArray(priceA);
                                totalAmountA = totalAmountL.toArray(totalAmountA);
                                quantityA = quantityL.toArray(quantityA);
                                Intent i = new Intent(getApplicationContext(), order.class);
                                Bundle b = new Bundle();
                                b.putStringArray("item", selectedA);
                                b.putStringArray("price", priceA);
                                b.putStringArray("totalAmount", totalAmountA);
                                b.putStringArray("quantity", quantityA);
                                i.putExtras(b);
                                i.putExtra("tp", String.valueOf(tp));
                                startActivity(i);
                            }
                        }
                    }
                }
            });
    }

    private void disableItem() {
        spin.setVisibility(View.GONE);
        spin2.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
    }

    public void addingListeners() {
        drink = (CheckBox) findViewById(R.id.checkBox);
        dessert = (CheckBox) findViewById(R.id.checkBox2);
        snack = (CheckBox) findViewById(R.id.checkBox3);
        tv = (TextView) findViewById(R.id.textView);
        tvResult = (TextView) findViewById(R.id.textView2);
        order = (Button) findViewById(R.id.button3);
        lv = (ListView) findViewById(R.id.lv1);
        cart = (Button) findViewById(R.id.button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Quantity,R.layout.custom_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quantity =adapterView.getItemAtPosition(i).toString();
                if(quantity.equals("Select the Quantity"))
                    quantity = "0";;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                quantity = "0";
            }
        });
    }
    public void setVisible(){
        flag =2;
        tv.setVisibility(View.VISIBLE);
        spin2.setVisibility(View.VISIBLE);
        spin.setVisibility(View.VISIBLE);
    }

    public void addFunctionality(View view) {
        flag =2;
//        Toast.makeText(this,String.valueOf(flag),Toast.LENGTH_SHORT).show();
        if(drink.isChecked()) {
            flag =2;
            setVisible();
            if(drink.isChecked()&&(snack.isChecked()||dessert.isChecked())){
                if(snack.isChecked())
                    snack.toggle();
                if(dessert.isChecked())
                    dessert.toggle();
            }
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.drinks,R.layout.custom_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected  = adapterView.getItemAtPosition(i).toString();
                    if(selected.equals("Apple Cider")) {
                        price =30;
                        setVisible();
//                        Toast.makeText(getApplicationContext(),quantity,Toast.LENGTH_SHORT).show();
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected.equals("Peanut Punch")) {
                        setVisible();
                        price =30;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected.equals("Shikanji")) {
                        setVisible();
                        price=20;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected .equals("Ginger Tea")) {
                        setVisible();
                        price=20;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    selected = "Nothing is Selected";
                }
            });
        }

        if(dessert.isChecked()) {
            flag =2;
            setVisible();
            if(dessert.isChecked()&&(snack.isChecked()||drink.isChecked())){
                if(snack.isChecked())
                    snack.toggle();
                if(drink.isChecked())
                    drink.toggle();
            }
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.desserts,R.layout.custom_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected  = adapterView.getItemAtPosition(i).toString();
                    if(selected.equals("Gulab Jamun")) {
                        price =30;
                        setVisible();
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected.equals("Rasgulla")) {
                        setVisible();
                        price =30;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected.equals("Pinni")) {
                        setVisible();
                        price=20;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected .equals("Bal Mithai")) {
                        setVisible();
                        price=20;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    selected = "Nothing is Selected";
                }
            });
        }

        if(snack.isChecked()) {
            flag =2;
            setVisible();
            if(snack.isChecked()&&(drink.isChecked()||dessert.isChecked())){
                if(drink.isChecked())
                    drink.toggle();
                if(dessert.isChecked())
                    dessert.toggle();
            }
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.snacks,R.layout.custom_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected  = adapterView.getItemAtPosition(i).toString();
                    if(selected.equals("Bhelpuri")) {
                        price =30;
                        setVisible();
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected.equals("Bonda")) {
                        setVisible();
                        price =30;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected.equals("Dabeli")) {
                        setVisible();
                        price=20;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                    if(selected .equals("Dhokla")) {
                        setVisible();
                        price=20;
                        totalAmount = Integer.parseInt(quantity)*price;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    selected = "Nothing is Selected";
                }
            });
        }
    }

    public void clear(View view) {
        str="";
        Toast.makeText(getApplicationContext(),"List is Emapty Now",Toast.LENGTH_SHORT).show();
        tvResult.setText("");
        totalAmount=0;
        c=0;
        disableItem();
        drink.setChecked(false);
        snack.setChecked(false);
        dessert.setChecked(false);
        selectedItemL.clear();
        priceL.clear();
        totalAmountL.clear();
        quantityL.clear();
        tp=0;
        flag=0;
    }
}
