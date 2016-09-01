package com.example.waseem.mcbosp;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Waseem on 28-04-2016.
 */
public class NotificationExample  extends Activity
{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btn_default_notification)).setOnClickListener(btnClick);
    }

    private void addDefaultNotification(){
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        int icon = R.drawable.icon;
        CharSequence text = "Notification Message ";
        CharSequence contentTitle = "Banking";
        CharSequence contentText = "when a loan has not been returned within the loan period.";
        long when = System.currentTimeMillis();

        Intent intent = new Intent(this, NotificationViewer.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification(icon,text,when);

        long[] vibrate = {0,100,200,300};
        notification.vibrate = vibrate;

        notification.ledARGB = Color.RED;
        notification.ledOffMS = 300;
        notification.ledOnMS = 300;

        notification.defaults |= Notification.DEFAULT_LIGHTS;
        //notification.flags |= Notification.FLAG_SHOW_LIGHTS;

       // notification.setLatestEventInfo(this, contentTitle, contentText, contentIntent);

        notificationManager.notify(Constants.NOTIFICATION_ID, notification);
    }

    private final View.OnClickListener btnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btn_default_notification:
                {
                    addDefaultNotification();

                    break;
                }
            }
        }
    };
}
