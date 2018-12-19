package com.w3engineers.jitpackbottomnav.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.Navigation
import com.w3engineers.jitpackbottomnav.App
import com.w3engineers.jitpackbottomnav.MainActivity
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.data.model.User_
import com.w3engineers.jitpackbottomnav.fragment.home.HomeFragmentDirections


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 12/17/2018 at 12:43 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 12/17/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

object NotificationUtil {

    val CHANNEL_NAME = "tele_mesh"
    val CHANNEL_ID = "notification_channel"
    fun showNotification() {
        val user = App.getBoxStore().boxFor(User::class.java).all
/*

        val args = Bundle().apply {
            putParcelable("user", user.get(0))
        }
*/

        // Prepare the pending intent, while specifying the graph and destination
        val pendingIndent = NavDeepLinkBuilder(App.getContext())
            .setGraph(R.navigation.bottom_nav_graph)
            .setDestination(R.id.user_profile)
            .createPendingIntent()


// Create notification instance
        val notification = NotificationCompat.Builder(App.getContext(), "navigation")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Explicit deep link")
            .setContentText("Clicking on this notification will take you to the destination fragment")
            .setContentIntent(pendingIndent)
            .setAutoCancel(true)
            .build()

// Display notification
        NotificationManagerCompat.from(App.getContext()).notify(10, notification)
    }

    fun showGeneralNotification() {
        val users = App.getBoxStore().boxFor(User::class.java).all
        val user = users.get(0)
        val context = App.getContext();
        val intent = Intent(context, MainActivity::class.java).also {
            it.putExtra("user", user)
            it.putExtra("for", "chat_page")
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            mNotificationManager?.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(context, CHANNEL_ID)
        } else {
            builder = NotificationCompat.Builder(context)
        }

        builder.setWhen(System.currentTimeMillis())
            .setContentText("New message received")
            .setContentTitle("Name")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(Notification.PRIORITY_HIGH).setVibrate(LongArray(0))
            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
            .setSmallIcon(R.mipmap.ic_launcher)

        val notification = builder.build()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifyId = Math.abs(user.userId.hashCode())
        notificationManager?.notify(notifyId, notification)

    }
}