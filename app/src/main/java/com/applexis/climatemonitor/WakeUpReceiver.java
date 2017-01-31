package com.applexis.climatemonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WakeUpReceiver extends BroadcastReceiver {
    public WakeUpReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            WidgetUpdateManager.registerBroadcastReceiver(context);
        }
        WidgetUpdateManager.updateAllWidgets(context);
    }
}
