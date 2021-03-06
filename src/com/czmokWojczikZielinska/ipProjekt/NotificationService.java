package com.czmokWojczikZielinska.ipProjekt;

/**
 * Created by Anna on 03.05.14.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;

public class NotificationService extends Service {

    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        // Getting Notification Service
        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(), MyActivity.class); //otwiera przypomnienie

        Notification notification = new Notification(R.drawable.biegacz,
                "Najwyższy czas pobiegac! :)", System.currentTimeMillis());

        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
                this.getApplicationContext(), 0, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notification.setLatestEventInfo(this.getApplicationContext(),
                "Asystent biegu", "Najwyższy czas pobiegać! :)",
                pendingNotificationIntent);

        mManager.notify(0, notification);

        Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notif);
        r.play();
        Vibrator v = (Vibrator) getApplicationContext().getSystemService(this.getApplicationContext().VIBRATOR_SERVICE);
        v.vibrate(500); //wibracja przez 500 ms
    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
