package com.server.azhar.customexpandablelistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Azhar on 3/28/2017.
 */

public class CustomAdapter extends BaseExpandableListAdapter {



    private Context c;
    private ArrayList<Team> team;
    private LayoutInflater inflater;

    public CustomAdapter(Context c,ArrayList<Team> team)
    {
        this.c=c;
        this.team=team;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return team.size();
    }

    @Override
    public int getChildrenCount(int groupPosw) {
        return  team.get(groupPosw).players.size();
    }

    @Override
    public Object getGroup(int groupPos) {
        return team.get(groupPos);
    }

    @Override
    public Object getChild(int groupPos, int childPos) {
        return team.get(groupPos).players.get(childPos);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
        //ONLY INFLATE XML TEAM ROW MODEL IF ITS NOT PRESENT,OTHERWISE REUSE IT
        if(convertView == null)
        {
            convertView=inflater.inflate(R.layout.our_team, null);
        }
        //GET GROUP/TEAM ITEM
        Team t=(Team) getGroup(groupPosition);
        //SET GROUP NAME
        TextView nameTv=(TextView) convertView.findViewById(R.id.textView1);
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView1);
        String name=t.Name;
        nameTv.setText(name);
        //ASSIGN TEAM IMAGES ACCORDING TO TEAM NAME
        if(name=="Man Utd")
        {
            img.setImageResource(R.drawable.manutd);
        }else if(name=="Chelsea")
        {
            img.setImageResource(R.drawable.chelsea);
        }else if(name=="Arsenal")
        {
            img.setImageResource(R.drawable.arsenal);
        }
        //SET TEAM ROW BACKGROUND COLOR
        convertView.setBackgroundColor(Color.LTGRAY);
        return convertView;
    }

    @Override
    public View getChildView(int groupPos, int childPos, boolean b, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.our_player, null);
        }
        //GET CHILD/PLAYER NAME
        String  child=(String) getChild(groupPos, childPos);
        //SET CHILD NAME
        TextView nameTv=(TextView) convertView.findViewById(R.id.textView1);
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView1);
        nameTv.setText(child);
        //GET TEAM NAME
        String teamName= getGroup(groupPos).toString();
        //ASSIGN IMAGES TO PLAYERS ACCORDING TO THEIR NAMES AN TEAMS
        if(teamName=="Man Utd")
        {
            if(child=="Wayne Rooney")
            {
                img.setImageResource(R.drawable.rooney)  ;
            }else if(child=="Ander Herera")
            {
                img.setImageResource(R.drawable.herera)  ;
            }else if(child=="Van Persie")
            {
                img.setImageResource(R.drawable.vanpersia)  ;
            }else if(child=="Juan Mata")
            {
                img.setImageResource(R.drawable.mata)  ;
            }
        }else if(teamName=="Chelsea")
        {
            if(child=="John Terry")
            {
                img.setImageResource(R.drawable.terry)  ;
            }else if(child=="Eden Hazard")
            {
                img.setImageResource(R.drawable.hazard)  ;
            }else if(child=="Oscar")
            {
                img.setImageResource(R.drawable.oscar)  ;
            }else if(child=="Diego Costa")
            {
                img.setImageResource(R.drawable.costa)  ;
            }
        }else if(teamName=="Arsenal")
        {
            if(child=="Jack Wilshere")
            {
                img.setImageResource(R.drawable.wilshere)  ;
            }else if(child=="Alexis Sanchez")
            {
                img.setImageResource(R.drawable.sanchez)  ;
            }else if(child=="Aaron Ramsey")
            {
                img.setImageResource(R.drawable.ramsey)  ;
            }else if(child=="Mesut Ozil")
            {
                img.setImageResource(R.drawable.ozil)  ;
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
