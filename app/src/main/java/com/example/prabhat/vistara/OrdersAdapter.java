package com.example.prabhat.vistara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabhat on 07-10-2017.
 */

public class OrdersAdapter extends ArrayAdapter {

    List list=new ArrayList();
    Context context,cx;
    static int pos;
    View view;
    static String p;


    public OrdersAdapter( Context context,  int resource) {
        super(context, resource);
        this.context=context;//IMPPPPPPPPPPPPPPPPPPPP
        this.cx=context;
    }

    public void add(Orders.Editdetails object){
        super.add(object);
        list.add(object);

    }


    public int getCount(){
        return list.size();
    }


    public Object getItem(int position){
        return list.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        OrdersAdapter.ContactHolder ch;
        View row;
        row=convertView;
        if(row==null){
            LayoutInflater lf=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=lf.inflate(R.layout.orderslist,parent,false);
            ch=new OrdersAdapter.ContactHolder();
            pos=position;
            ch.name=(TextView) row.findViewById(R.id.fooditem);
            ch.price=(TextView)row.findViewById(R.id.itemprice);
            ch.img=(ImageView)row.findViewById(R.id.imageorder);
            row.setTag(ch);

        }
        else{
            ch=(OrdersAdapter.ContactHolder)row.getTag();
            pos=position;
        }
        Orders.Editdetails c=(Orders.Editdetails) this.getItem(position);
        ch.name.setText(c.getName());
        ch.price.setText(c.getType());
        Picasso.with(context).load(c.getLang()).into(ch.img);

        return row;
    }


    static class ContactHolder{
        TextView name,price;
         ImageView img;
    }

}
