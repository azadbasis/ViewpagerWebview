package volleycom.azhar.volleyrecyclerviewmodel001.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import volleycom.azhar.volleyrecyclerviewmodel001.utility.AppController;

/**
 * Created by Azhar on 4/11/2017.
 */

public class NetworkConnectionReciever extends BroadcastReceiver {

    public static ConnectivityRecieverListener connectivityRecieverListener;
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(connectivityRecieverListener !=null){
            connectivityRecieverListener.OnNetworkChange(isConnected);
        }

    }

    public static boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) AppController.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }


    public interface ConnectivityRecieverListener{
        public void OnNetworkChange(boolean inConnected);
    }
}
