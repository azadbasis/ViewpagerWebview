package com.server.sync.azhar.sqlitesyncdemoone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Azhar on 3/31/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<Contact> arrayList = new ArrayList<>();

    public CustomAdapter(ArrayList<Contact> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtView.setText(arrayList.get(position).getName());
        int syncStatus = arrayList.get(position).getSyncStatus();

        if(syncStatus == DbContact.SYNC_STATUS_OK)
        {
            holder.imgView.setImageResource(R.mipmap.ic_ok);
        }else {
            holder.imgView.setImageResource(R.mipmap.ic_sync);
        }



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  static  class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView txtView;
        public MyViewHolder(View view) {
            super(view);
            imgView = (ImageView)view.findViewById(R.id.imgSync);
            txtView = (TextView)view.findViewById(R.id.textName);
        }



    }

}
