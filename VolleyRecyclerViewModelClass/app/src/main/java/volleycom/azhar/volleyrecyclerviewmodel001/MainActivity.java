package volleycom.azhar.volleyrecyclerviewmodel001;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import volleycom.azhar.volleyrecyclerviewmodel001.model.Employee;
import volleycom.azhar.volleyrecyclerviewmodel001.reciever.NetworkConnectionReciever;
import volleycom.azhar.volleyrecyclerviewmodel001.utility.AppController;
import volleycom.azhar.volleyrecyclerviewmodel001.utility.Constant;
import volleycom.azhar.volleyrecyclerviewmodel001.utility.CustomAdapter;

public class MainActivity extends AppCompatActivity implements NetworkConnectionReciever.ConnectivityRecieverListener {

    List<Employee> employeeList;
    RecyclerView recyclerView;
    Boolean isConnected;
    RelativeLayout myRelativeLayout;
    public static final String NA = "NA";
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }

    private void findViewById() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        myRelativeLayout = (RelativeLayout)findViewById(R.id.activity_main);
        employeeList = new ArrayList<>();
        showEmployee();
    }

    public void showEmployee(){
        if (checkConnectivity()){

            try {
                getEmployee();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            showSnackBar();
        }

    }
    public boolean checkConnectivity(){
        return NetworkConnectionReciever.isConnected();
        // showSnackBar(isConnected);
    }

    @Override
    public void OnNetworkChange(boolean inConnected) {
        this.isConnected = inConnected;
    }
    public void showSnackBar(){

        //into threa

                    Snackbar.make(myRelativeLayout, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.btn_settings), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            }).setActionTextColor(Color.RED).show();


    }



    public void getEmployee()throws Exception{

        String url = Constant.URL;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Result",response);
                parseEmployee(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


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

    public void parseEmployee(String response) {

        JSONArray jsonArray;
        Employee employee = new Employee();
        try {
            jsonArray = new JSONArray(new String(response));
            for(int i = 0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (isContain(jsonObject,"postId"))
                    employee.postId = jsonObject.getInt("postId");
                else {
                    employee.postId = 0;
                }
                if (isContain(jsonObject,"id")){
                    employee.id = jsonObject.getInt("id");
                }else {
                    employee.id = 0;
                }
                if (isContain(jsonObject,"name")){
                    employee.name = jsonObject.getString("name");
                }else {
                    employee.name = NA;
                }
                if(isContain(jsonObject,"email")){
                    employee.email = jsonObject.getString("email");
                }else {
                    employee.email = NA;
                }
               if(isContain(jsonObject,"body")){
                   employee.body = jsonObject.getString("body");
               }else {
                   employee.body = NA;
               }
                employeeList.add(employee);

            }
           customAdapter  = new CustomAdapter( employeeList,MainActivity.this);
            recyclerView.setAdapter(customAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityReciever(this);
    }

    public boolean isContain(JSONObject jsonObject,String key){

        return jsonObject != null&& jsonObject.has(key) && !jsonObject.isNull(key) ? true:false;
    }
}
