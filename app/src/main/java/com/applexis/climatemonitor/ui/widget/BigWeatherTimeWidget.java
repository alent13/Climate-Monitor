package com.applexis.climatemonitor.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.applexis.climatemonitor.R;
import com.applexis.climatemonitor.WidgetUpdateManager;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class BigWeatherTimeWidget extends AppWidgetProvider {

    private static String[] dayOfWeekList;
    private static String[] monthList;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.big_weather_time_widget);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        views.setTextViewText(R.id.big_weather_time_text,
                String.format(Locale.ENGLISH, "%2s:%2s",
                        hours < 10 ? "0" + hours : hours,
                        minutes < 10 ? "0" + minutes : minutes));

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        dayOfWeekList = new String[]{
                context.getString(R.string.sunday),
                context.getString(R.string.monday),
                context.getString(R.string.tuesday),
                context.getString(R.string.wednesday),
                context.getString(R.string.thursday),
                context.getString(R.string.friday),
                context.getString(R.string.saturday)};

        monthList = new String[]{
                context.getString(R.string.january), context.getString(R.string.february),
                context.getString(R.string.march), context.getString(R.string.april),
                context.getString(R.string.may), context.getString(R.string.june),
                context.getString(R.string.july), context.getString(R.string.august),
                context.getString(R.string.september), context.getString(R.string.october),
                context.getString(R.string.november), context.getString(R.string.december)};
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        WidgetUpdateManager.registerBroacastReceiver(context);
        WidgetUpdateManager.updateBigWeatherWidget(context);
    }

    @Override
    public void onDisabled(Context context) {
        WidgetUpdateManager.updateBigWeatherWidget(context);
    }
}

