package com.server.sync.azhar.sqlitesyncdemoone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azhar on 4/3/2017.
 */

public class NetworkController extends BroadcastReceiver {



    @Override
    public void onReceive(final Context context, Intent intent) {

        if(checkInternetConnection(context))
        {
            final DBHelper dbHelper = new DBHelper(AppController.getAppContext());
            final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            Cursor cursor = dbHelper.readFromLocalDatabase(sqLiteDatabase);



            while (cursor.moveToNext()){

                final String Name = cursor.getString(cursor.getColumnIndex(DbContact.NAME));
                final int syncstatus = cursor.getInt(cursor.getColumnIndex(DbContact.SYNC_STATUS));

                final int id = cursor.getInt(cursor.getColumnIndex(DbContact.ID));


                if(syncstatus == DbContact.SYNC_STATUS_FAILED)
                {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String responses = jsonObject.getString("response");

                                if(responses.equals("ok")){

                                    dbHelper.updateLocalDatabase(Name,DbContact.SYNC_STATUS_OK,sqLiteDatabase,id);

                                    context.sendBroadcast(new Intent(DbContact.UI_UPDATE_BROADCAST));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("name",Name);
                            return params;
                        }
                    }
                ;
                    AppController.getInstance().addToRequestQueue(stringRequest);
                }

            }
            dbHelper.close();
        }

    }



    public boolean checkInternetConnection(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected() );
    }
}
