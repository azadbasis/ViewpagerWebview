package com.server.azhar.listviewsectionheaderfooter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Azhar on 3/26/2017.
 */

public class CustomAdapter extends BaseAdapter {

    ArrayList<Object> players;
    Context c;
    static final int  ROW =0;
    static final int HEADER =1;
    LayoutInflater layoutInflater;

    public CustomAdapter( Context context,ArrayList<Object> players) {

        this.c = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position)instanceof Player)
        {
            return ROW;
        }
            return HEADER;


    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        int type = getItemViewType(position);

        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) {

            switch (type) {
                case ROW:
                   view = layoutInflater.inflate(R.layout.model,null);
                    break;
                case HEADER:
                    view = layoutInflater.inflate(R.layout.header,null);
                    break;
                default:
                    break;

            }

        }

        switch (type){

            case ROW:
                Player p = (Player) getItem(position);
                TextView txtView = (TextView)view.findViewById(R.id.txtView);
                ImageView imgView = (ImageView)view.findViewById(R.id.imgView);
                txtView.setText(p.getName());
                imgView.setImageResource(p.getImage());
                break;

            case HEADER:
                String pos = (String)getItem(position);
                TextView headerView = (TextView)view.findViewById(R.id.headeView);
                headerView.setText(pos);
                headerView.setBackgroundColor(Color.CYAN);

                default:
                    break;
        }
        return view;
    }
}
