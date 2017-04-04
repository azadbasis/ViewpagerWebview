package com.server.sync.azhar.sqlitesyncdemoone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

import static com.server.sync.azhar.sqlitesyncdemoone.R.id.nameEt;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    EditText nameEt;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapter adapter;
    ArrayList<Contact> arrayList = new ArrayList<Contact>();
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        nameEt = (EditText)findViewById(R.id.nameEt);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new CustomAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        readFromLocalStorage();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                   readFromLocalStorage();

              //  DBHelper dbHelper = new DBHelper(context);
               // dbHelper.updateLocalDatabase();
            }
        };
    }
    public   void  submitName(View view)
    {
        String name = nameEt.getText().toString();
        saveToAppServer(name);
        nameEt.setText(" ");


    }


    public  void readFromLocalStorage(){


        arrayList.clear();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.readFromLocalDatabase(sqLiteDatabase);
        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(DbContact.NAME));
            int sync_status = cursor.getInt(cursor.getColumnIndex(DbContact.SYNC_STATUS));
            arrayList.add(new Contact(name,sync_status));
        }

        adapter.notifyDataSetChanged();
        cursor.close();
        dbHelper.close();
    }
    private void saveToAppServer(final String name){



        if(checkInternetConnection())
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String responses = jsonObject.getString("response");

                        if(responses.equals("ok"))
                        {
                            saveToLocalStorage(name,DbContact.SYNC_STATUS_OK);
                        }else{
                            saveToLocalStorage(name,DbContact.SYNC_STATUS_FAILED);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    saveToLocalStorage(name,DbContact.SYNC_STATUS_FAILED);

                }
            })

            {
                //you can add a simple code block with { /* your code */ } in there,
                // which gets executed on object creation.


                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("name",name);
                    return params;
                }
            }
                    ;
            AppController.getInstance().addToRequestQueue(stringRequest);

        }else{
          saveToLocalStorage(name,DbContact.SYNC_STATUS_FAILED);
        }

    }

    public boolean checkInternetConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected() );
    }
    private  void saveToLocalStorage(String name, int syncstatus){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        dbHelper.saveToLocalDatabase(name,syncstatus,sqLiteDatabase);
        readFromLocalStorage();
        dbHelper.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver,new IntentFilter(DbContact.UI_UPDATE_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }


}
