package com.applexis.climatemonitor;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.applexis.climatemonitor.ui.widget.BigWeatherTimeWidget;
import com.applexis.climatemonitor.ui.widget.SimpleClockWidget;

import java.util.Calendar;

/**
 * Created by applexis on 31.01.2017.
 */

public class WidgetUpdateManager {

    public static final String ACTION_CLOCK_UPDATE = "com.applexis.climatemonitor.ACTION_CLOCK_UPDATE";

    public static void registerBroadcastReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED);
        context.getApplicationContext().registerReceiver(new WakeUpReceiver(), intentFilter);
    }

    public static void updateSimpleWidget(Context context) {
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, SimpleClockWidget.class));
        SimpleClockWidget widget = new SimpleClockWidget();
        widget.onUpdate(context, AppWidgetManager.getInstance(context), ids);
        context.startService(new Intent(context, InfoUpdateService.class));
    }

    public static void updateBigWeatherWidget(Context context) {
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, BigWeatherTimeWidget.class));
        BigWeatherTimeWidget widget2 = new BigWeatherTimeWidget();
        widget2.onUpdate(context, AppWidgetManager.getInstance(context), ids);
        context.startService(new Intent(context, InfoUpdateService.class));
    }

    public static void updateAllWidgets(Context context) {
        updateSimpleWidget(context);
        updateBigWeatherWidget(context);
    }

    public static void createAlarm(Context context, Intent intent) {
        final AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        // schedules updates so they occur on the top of the minute
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MINUTE, 1);
        alarmManager.setRepeating(AlarmManager.RTC, c.getTimeInMillis(), 1000 * 60,
                PendingIntent.getService(context, 0, intent,
                        PendingIntent.FLAG_ONE_SHOT));
    }

}
