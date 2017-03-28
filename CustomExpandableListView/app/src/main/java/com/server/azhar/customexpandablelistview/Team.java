package com.server.azhar.customexpandablelistview;

import java.util.ArrayList;

/**
 * Created by Azhar on 3/28/2017.
 */

public class Team {

    //PROPERTIES OF A SINGLE TEAM
    public String Name;
    public String Image;
    public ArrayList<String> players=new ArrayList<String>();
    public Team(String Name)
    {
        this.Name=Name;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Name;
    }
}
