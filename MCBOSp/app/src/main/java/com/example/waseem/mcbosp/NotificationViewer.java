package com.example.waseem.mcbosp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;

/**
 * Created by Waseem on 28-04-2016.
 */
public class NotificationViewer  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_viewer);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(Constants.NOTIFICATION_ID);
    }
}
