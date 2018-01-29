package rocks.lechick.android.walkmehome.utils;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;

import rocks.lechick.android.walkmehome.R;

/*
 * Created by Gleb
 * TulaCo 
 * 1/19/2018
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SmsJobService extends JobService {
    private static final int JOB_ID = 1;
    private static final int ONE_MIN = 60 * 1000;

    public static void schedule(Context context, String phone) {
        LocationHelper.setLocationProviderSettings(context);
        ComponentName component = new ComponentName(context, SmsJobService.class);
        PersistableBundle bundle = new PersistableBundle();
        bundle.putString("phone", phone);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, component)
                .setExtras(bundle)
                .setMinimumLatency(1000);

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }

    public static void stop(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOB_ID);
    }

    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.e("SMS", "onStartJob");
        LocationHelper.getCurrentLocation(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                String message = getString(R.string.sms_text_base) + " " +
                        getString(R.string.google_maps_link) + location.getLatitude() + "," + location.getLongitude();
                SmsHelper.sendSms(SmsJobService.this, params.getExtras().getString("phone"), message);
            }
        });

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
