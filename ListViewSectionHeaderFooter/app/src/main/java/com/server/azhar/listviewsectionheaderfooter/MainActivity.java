package com.server.azhar.listviewsectionheaderfooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String[] name ={"Azharul Islam","Anwarul Islam","Anisur Rahman"};
    int[] image ={R.drawable.p2,R.drawable.p3,R.drawable.p4};
    ListView listView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView)findViewById(R.id.list);
        adapter = new CustomAdapter(this,getPlayer());
        listView.setAdapter(adapter);
    }

        private ArrayList<Object> getPlayer() {
        ArrayList<Object> player = new ArrayList<>();

            Player p = new Player(name[0],image[0]);
            player.add("==HE IS A PROGRAMMER==");
            player.add(p);
           Player p1 = new Player(name[1],image[2]);
            player.add("=== HE IS A ACCOUNTANT===");
            player.add(p1);
            Player  p2 = new Player(name[2],image[2]);
            player.add("===HE IS A FORESTER===");
            player.add(p2);

               return  player;

    }


}

