package com.applexis.climatemonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class WakeUpReceiver extends BroadcastReceiver {
    public WakeUpReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Received " + intent.getAction(), Toast.LENGTH_SHORT).show();
        WidgetUpdateManager.updateAllWidgets(context);
    }
}
