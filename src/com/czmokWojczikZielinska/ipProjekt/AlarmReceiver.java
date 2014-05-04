package com.czmokWojczikZielinska.ipProjekt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {

        Intent myIntent = new Intent(context, NotificationService.class);
        context.startService(myIntent);
    }

}
