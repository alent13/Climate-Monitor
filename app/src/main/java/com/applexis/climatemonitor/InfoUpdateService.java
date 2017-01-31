package com.applexis.climatemonitor;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;

public class InfoUpdateService extends Service {

    public InfoUpdateService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, intent.getAction(), Toast.LENGTH_SHORT).show();
        createAlarm(this, new Intent(WidgetUpdateManager.ACTION_CLOCK_UPDATE));
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        createAlarm(this, new Intent(this, InfoUpdateService.class));
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        createAlarm(this, new Intent(this, InfoUpdateService.class));
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
                PendingIntent.getBroadcast(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
