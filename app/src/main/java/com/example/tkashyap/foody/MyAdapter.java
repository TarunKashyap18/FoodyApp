package com.example.tkashyap.foody;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * Created by TARUN KASHYAP on 10/18/2017.
 */

public class MyAdapter extends ArrayAdapter {
    String[] priceA;
    String[] totalpriceA;
    String[] itemNameA;
    String[] amountA;

    public MyAdapter(Context context,String[] item,String[] price1,String [] totalprice1,String [] quantity1) {
        super(context, R.layout.custom_list_view,R.id.textView7,item);
        this.priceA = price1;
        this.totalpriceA = totalprice1;
        this.itemNameA = item;
        this.amountA = quantity1;
    }
    public View getView(int position , View convertView , ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_list_view,parent,false);
        TextView myItem = (TextView) row.findViewById(R.id.textView7);
        TextView myQuantity = (TextView) row.findViewById(R.id.textView8);
        TextView myPrice = (TextView) row.findViewById(R.id.textView9);
        myItem.setText(itemNameA[position]);
        myQuantity.setText(amountA[position]);
        myPrice.setText(priceA[position]);
        return row;
    }

}
