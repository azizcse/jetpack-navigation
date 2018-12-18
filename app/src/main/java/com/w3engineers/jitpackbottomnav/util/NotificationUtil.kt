package com.w3engineers.jitpackbottomnav.util

import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.Navigation
import com.w3engineers.jitpackbottomnav.App
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
}