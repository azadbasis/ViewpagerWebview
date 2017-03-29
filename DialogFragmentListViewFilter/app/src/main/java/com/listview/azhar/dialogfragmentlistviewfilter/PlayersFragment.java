package com.listview.azhar.dialogfragmentlistviewfilter;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;


public class PlayersFragment extends DialogFragment {


    Button btn;
    ListView lv;
    SearchView sv;
    ArrayAdapter<String> adapter;
    String[] players={"Lionel Messi","Christiano Ronaldo","Neymar","Gareth Bale"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView=inflater.inflate(R.layout.fragment_players, null);
        //SET TITLE DIALOG TITLE
        getDialog().setTitle("Best Players In The World");
        //BUTTON,LISTVIEW,SEARCHVIEW INITIALIZATIONS
        lv=(ListView) rootView.findViewById(R.id.listView1);
        sv=(SearchView) rootView.findViewById(R.id.searchView1);
        btn=(Button) rootView.findViewById(R.id.dismiss);
        //CREATE AND SET ADAPTER TO LISTVIEW
        adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,players);
        lv.setAdapter(adapter);
        //SEARCH
        sv.setQueryHint("Search..");
        sv.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String txt) {
                // TODO Auto-generated method stub
                return false;
            }
            @Override
            public boolean onQueryTextChange(String txt) {
                // TODO Auto-generated method stub
                adapter.getFilter().filter(txt);
                return false;
            }
        });
        //BUTTON
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });
        return rootView;
    }
}