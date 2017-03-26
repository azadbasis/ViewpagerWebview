package com.tutorials.hp.listviewlongclick;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Oclemmy on 3/27/2016 for ProgrammingWizards Channel.
 */
public class MyViewHolder implements View.OnLongClickListener {

    ImageView img;
    TextView nameTxt;
    ItemLongClickListener itemLongClickListener;

    public MyViewHolder(View v) {
        img= (ImageView) v.findViewById(R.id.movieImage);
        nameTxt= (TextView) v.findViewById(R.id.nameTxt);

        v.setOnLongClickListener(this);

    }

    public void setItemLongClickListener(ItemLongClickListener ic)
    {
        this.itemLongClickListener=ic;
    }

    @Override
    public boolean onLongClick(View v) {
        this.itemLongClickListener.onLongItemClick(v);
        return false;
    }
}
