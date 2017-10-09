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

public class MoviesAdapter extends ArrayAdapter{

    List list=new ArrayList();
    Context context,cx;
    static int pos;
    View view;
    static String p;


    public MoviesAdapter( Context context,  int resource) {
        super(context, resource);
        this.context=context;//IMPPPPPPPPPPPPPPPPPPPP
        this.cx=context;
    }

    public void add(Entertainment.Editdetails object){
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
        ContactHolder ch;
        View row;
        row=convertView;
        if(row==null){
            LayoutInflater lf=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=lf.inflate(R.layout.movieslist,parent,false);
            ch=new ContactHolder();
            pos=position;
            ch.name=(TextView) row.findViewById(R.id.moviename);
            ch.lang=(TextView)row.findViewById(R.id.movielang);
            ch.type=(TextView)row.findViewById(R.id.movietype);
            ch.thumbanil=(ImageView)row.findViewById(R.id.movieimage);
            row.setTag(ch);

        }
        else{
            ch=(ContactHolder)row.getTag();
            pos=position;
        }
        Entertainment.Editdetails c=(Entertainment.Editdetails) this.getItem(position);
        ch.name.setText(c.getName());
        ch.lang.setText(c.getLang());
        ch.type.setText(c.getType());
        Picasso.with(context).load(c.getThumb()).into(ch.thumbanil);
        return row;
    }


    static class ContactHolder{
        TextView name,lang,type;
        ImageView thumbanil;

    }

}
