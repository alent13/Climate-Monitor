package com.applexis.climatemonitor;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.applexis.climatemonitor.ui.widget.BigWeatherTimeWidget;
import com.applexis.climatemonitor.ui.widget.SimpleClockWidget;

/**
 * Created by applexis on 31.01.2017.
 */

public class WidgetUpdateManager {

    public static void registerBroacastReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        context.getApplicationContext().registerReceiver(new WakeUpReceiver(), intentFilter);
    }

    public static void updateSimpleWidget(Context context) {
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, SimpleClockWidget.class));
        SimpleClockWidget widget = new SimpleClockWidget();
        widget.onUpdate(context, AppWidgetManager.getInstance(context), ids);
    }

    public static void updateBigWeatherWidget(Context context) {
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, BigWeatherTimeWidget.class));
        BigWeatherTimeWidget widget2 = new BigWeatherTimeWidget();
        widget2.onUpdate(context, AppWidgetManager.getInstance(context), ids);
    }

    public static void updateAllWidgets(Context context) {
        updateSimpleWidget(context);
        updateBigWeatherWidget(context);
    }

}
