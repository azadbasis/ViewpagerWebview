package com.server.azhar.customexpandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //THE EXPANDABLE
        ExpandableListView elv=(ExpandableListView) findViewById(R.id.expandableListView1);
        final ArrayList<Team> team=getData();
        //CREATE AND BIND TO ADAPTER
        CustomAdapter adapter=new CustomAdapter(this, team);
        elv.setAdapter(adapter);
        //SET ONCLICK LISTENER
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPos,
                                        int childPos, long id) {
                Toast.makeText(getApplicationContext(), team.get(groupPos).players.get(childPos), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    //ADD AND GET DATA
    private ArrayList<Team> getData()
    {
      //  Team t1=new Team("Man Utd");
        Team t1=new Team("Azharul islam");
        t1.players.add("Wayne Rooney");
        t1.players.add("Van Persie");
        t1.players.add("Ander Herera");
        t1.players.add("Juan Mata");
       // Team t2=new Team("Arsenal");
        Team t2=new Team("Anwarul Islam");
        t2.players.add("Aaron Ramsey");
        t2.players.add("Mesut Ozil");
        t2.players.add("Jack Wilshere");
        t2.players.add("Alexis Sanchez");
      //  Team t3=new Team("Chelsea");
        Team t3=new Team("Anisur Rahman");
        t3.players.add("John Terry");
        t3.players.add("Eden Hazard");
        t3.players.add("Diego Costa");
        t3.players.add("Oscar");
        ArrayList<Team> allTeams=new ArrayList<Team>();
        allTeams.add(t1);
        allTeams.add(t2);
        allTeams.add(t3);
        return allTeams;
    }

}
