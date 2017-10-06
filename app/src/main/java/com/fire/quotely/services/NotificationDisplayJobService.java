package com.fire.quotely.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.fire.quotely.R;
import com.fire.quotely.ui.fragments.TitleFragment;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by LLL-Brad on 10/5/2017.
 */

public class NotificationDisplayJobService extends JobService {


    @Override
    public boolean onStartJob(JobParameters job) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Quotely")
                .setContentText("Don't forget to see your quote of the day!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setGroupSummary(true)
                .setChannelId("entertainment")
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setGroup("entertainment");


        Intent resultIntent = new Intent(this, TitleFragment.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        builder.setContentIntent(resultPendingIntent);

        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
