package com.example.learning_app.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;

public class UtilitiesCheck {
     public Context context;
    public UtilitiesCheck(Context context) {
        this.context=context;
    }
    public   boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
        return true;
    }
        return false;
}
}
