package rocks.lechick.android.walkmehome.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;

import rocks.lechick.android.walkmehome.R;

/*
 * Created by Gleb
 * TulaCo 
 * 1/19/2018
 */

public class SmsService extends Service {

    public static final int SERVICE_ID = 100;

    public static void schedule(Context context, String phone) {
        LocationHelper.setLocationProviderSettings(context);
        Intent intent = new Intent(context, SmsService.class);
        intent.putExtra("phone", phone);
        PendingIntent pendingIntent = PendingIntent.getService(context, SmsService.SERVICE_ID, intent, 0);
        AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        alarm.cancel(pendingIntent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60*1000, pendingIntent);
    }

    public static void stop(Context context) {
        Intent intent = new Intent(context, SmsService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, SmsService.SERVICE_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        context.stopService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.e("SMS", "onStartCommand");
        LocationHelper.getCurrentLocation(this, location -> {
            if(location == null)
                return;
            String message = getString(R.string.sms_text_base) + " " +
                    getString(R.string.google_maps_link) + location.getLatitude() + "," + location.getLongitude();
            SmsHelper.sendSms(SmsService.this, intent.getStringExtra("phone"), message);
            stopSelf();
        });

        return super.onStartCommand(intent, flags, startId);
    }

}
