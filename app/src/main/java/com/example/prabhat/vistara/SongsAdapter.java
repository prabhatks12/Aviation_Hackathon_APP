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

public class SongsAdapter extends ArrayAdapter{
    List list=new ArrayList();
    Context context,cx;
    static int pos;
    View view;
    static String p;


    public SongsAdapter( Context context,  int resource) {
        super(context, resource);
        this.context=context;//IMPPPPPPPPPPPPPPPPPPPP
        this.cx=context;
    }

    public void add(Songs.Editdetails object){
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
        SongsAdapter.ContactHolder ch;
        View row;
        row=convertView;
        if(row==null){
            LayoutInflater lf=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=lf.inflate(R.layout.songslist,parent,false);
            ch= new SongsAdapter.ContactHolder();
            pos=position;
            ch.name=(TextView) row.findViewById(R.id.songname);
            ch.lang=(TextView)row.findViewById(R.id.songlang);
            ch.type=(ImageView) row.findViewById(R.id.songimage);
            row.setTag(ch);

        }
        else{
            ch=(SongsAdapter.ContactHolder)row.getTag();
            pos=position;
        }
        Songs.Editdetails c=(Songs.Editdetails) this.getItem(position);
        ch.name.setText(c.getName());
        ch.lang.setText(c.getLang());
        Picasso.with(context).load(c.getThumb()).into(ch.type);
        return row;
    }


    static class ContactHolder{
        TextView name,lang;
        ImageView type;

    }

}
