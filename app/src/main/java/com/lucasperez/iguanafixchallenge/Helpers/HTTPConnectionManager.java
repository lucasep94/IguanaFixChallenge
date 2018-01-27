package com.lucasperez.iguanafixchallenge.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Lukas on 25/01/2018.
 */

public class HTTPConnectionManager {
    public static boolean isNetworkingOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeConnection = cm.getActiveNetworkInfo();
        Boolean isOnline = (activeConnection != null) && activeConnection.isConnected();
        return isOnline;
    }
}
