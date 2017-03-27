package com.server.azhar.serverdatawithvolleyone;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import utility.AppControler;
import reciever.ConnectivityReciever;
import utility.Constants;

public class MainActivity extends AppCompatActivity implements ConnectivityReciever.ConnectivityRecieverListener {

    Button checkSnackBarBTN;
    RelativeLayout myRelativeLayout;
    public boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myRelativeLayout = (RelativeLayout)findViewById(R.id.activity_main);
        checkSnackBarBTN = (Button)findViewById(R.id.checkSnackbar);
        checkSnackBarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if( checkConnectivity()){
                  try {
                      getAllPosts();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }else {
                  showSnackBar();
              }
            }
        });

    }
    public boolean checkConnectivity(){
        return ConnectivityReciever.isConnected();
       // showSnackBar(isConnected);
    }

    public void showSnackBar(){

    //into thread

        new Thread(new Runnable() {
            public void run() {
                try {


                        Snackbar.make(myRelativeLayout, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                                .setAction(getString(R.string.btn_settings), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                    }
                                }).setActionTextColor(Color.RED).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        }).start();
    }



    @Override
    public void OnNetworkChange(boolean inConnected) {
       // showSnackBar(inConnected);
        this.isConnected = inConnected;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppControler.getInstance().setConnectivityReciever(this);
    }




    //1. ANDROID 4.4  KITKAT ONWORD USE THIS METHOD FOR FULL SCREEN;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        View decorView = getWindow().getDecorView();

        if (hasFocus) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

    }



    public  void getAllPosts()throws Exception

    {

       String TAG = "POST";
       String url = Constants.POSTS_URL;
        StringRequest objectRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("response",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getMessage());
            }
        });

        AppControler.getInstance().addToRequestQueue(objectRequest,TAG);
    }

}
