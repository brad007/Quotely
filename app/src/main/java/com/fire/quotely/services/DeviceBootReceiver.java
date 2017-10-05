package com.fire.quotely.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by LLL-Brad on 7/31/2017.
 */

public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context pContext, Intent pIntent) {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(pContext));
        Job myJob = dispatcher.newJobBuilder()
                .setService(NotificationDisplayJobService.class) // the JobService that will be called
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(24*60*60, 25*60*60))
                .setTag("my-unique-tag")        // uniquely identifies the job
                .build();

        dispatcher.mustSchedule(myJob);
    }
}
