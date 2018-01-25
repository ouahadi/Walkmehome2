package com.example.android.walkmehome.utils;
/*
 * Created by Gleb
 * TulaCo 
 * 1/19/2018
 */

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsManager;

import com.example.android.walkmehome.R;

import java.util.List;

public class SmsHelper {

    public static void sendSms(Context context, String phone, String message) {
        SmsManager sms = SmsManager.getDefault();
        List<String> messages = sms.divideMessage(message);
        for (String msg : messages) {
            PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent("SMS_SENT"), 0);
            PendingIntent deliveredIntent = PendingIntent.getBroadcast(context, 0, new Intent("SMS_DELIVERED"), 0);
            sms.sendTextMessage(phone, null, msg, sentIntent, deliveredIntent);
        }
    }

    public static void sendEmergencySms(Context context, String phone) {
        LocationHelper.getCurrentLocation(context, location -> {
            sendSms(context, phone, context.getString(R.string.emergency)
                    + context.getString(R.string.latitude) + " " + location.getLatitude()
                    + " " + context.getString(R.string.longitude)+ location.getLongitude());
        });
    }

    public static void scheduleSms(Context context, String phone) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SmsJobService.schedule(context, phone);
        } else {
            SmsService.schedule(context, phone);
        }
    }

    public static void stopSms(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SmsJobService.stop(context);
        } else {
            SmsService.stop(context);
        }
    }
}
