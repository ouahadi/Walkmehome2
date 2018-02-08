package rocks.lechick.android.walkmehome.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rocks.lechick.android.walkmehome.LocationServerDTO;
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
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60*4000, pendingIntent);
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

            String android_id = Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());
            ApiHelper.sendRequest(new LocationServerDTO(android_id, currentDateandTime, location.getLatitude() + "", location.getLongitude() + ""));
            stopSelf();
        });

        return super.onStartCommand(intent, flags, startId);
    }

}
