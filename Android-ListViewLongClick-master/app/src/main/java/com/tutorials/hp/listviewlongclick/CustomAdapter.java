package com.tutorials.hp.listviewlongclick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Oclemmy on 3/27/2016 for ProgrammingWizards Channel.
 */
public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Movie> movies;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Movie> movies) {
        this.c = c;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //SHALL RETURN A ROW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        //CONVERT FROM XML TO JAVA
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        //BIND DATA TO VIEWS
        MyViewHolder holder=new MyViewHolder(convertView);
        holder.nameTxt.setText(movies.get(position).getName());
        holder.img.setImageResource(movies.get(position).getImage());

        holder.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onLongItemClick(View v) {
                Toast.makeText(c,movies.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}
