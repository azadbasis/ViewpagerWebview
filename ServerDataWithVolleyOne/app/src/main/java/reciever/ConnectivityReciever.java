package reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import utility.AppControler;

public class ConnectivityReciever extends BroadcastReceiver {

public static ConnectivityRecieverListener connectivityRecieverListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(connectivityRecieverListener !=null){
            connectivityRecieverListener.OnNetworkChange(isConnected);
        }
    }

public static boolean isConnected(){
    ConnectivityManager connectivityManager = (ConnectivityManager) AppControler.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

}

    public interface ConnectivityRecieverListener{
        public void OnNetworkChange(boolean inConnected);
    }
}
