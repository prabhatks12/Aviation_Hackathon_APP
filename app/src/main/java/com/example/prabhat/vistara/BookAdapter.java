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

public class BookAdapter  extends ArrayAdapter{
    List list=new ArrayList();
    Context context,cx;
    static int pos;
    View view;
    static String p;


    public BookAdapter( Context context,  int resource) {
        super(context, resource);
        this.context=context;//IMPPPPPPPPPPPPPPPPPPPP
        this.cx=context;
    }

    public void add(Book.Editdetails object){
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
        BookAdapter.ContactHolder ch;
        View row;
        row=convertView;
        if(row==null){
            LayoutInflater lf=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=lf.inflate(R.layout.booklist,parent,false);
            ch=new BookAdapter.ContactHolder();
            pos=position;
            ch.name=(TextView) row.findViewById(R.id.bookname);
            ch.author=(TextView)row.findViewById(R.id.bookauthor);
            ch.img=(ImageView)row.findViewById(R.id.bookimagess);
            row.setTag(ch);

        }
        else{
            ch=(BookAdapter.ContactHolder)row.getTag();
            pos=position;
        }
        Book.Editdetails c=(Book.Editdetails) this.getItem(position);
        ch.name.setText(c.getName());
        ch.author.setText(c.getAuthor());
        Picasso.with(context).load(c.getImg()).into(ch.img);

        return row;
    }


    static class ContactHolder{
        TextView name,author;
        ImageView img;

    }

}
