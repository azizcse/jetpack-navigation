package com.w3engineers.jitpackbottomnav.util

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.w3engineers.jitpackbottomnav.R


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/27/2018 at 12:04 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/27/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

object  AnimUtil {
    fun slideUp(context: Context, view: View) {
        if(view.visibility == View.GONE) {
            val anim = AnimationUtils.loadAnimation(context, R.anim.slid_up)
            view.visibility = View.VISIBLE
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {}

                override fun onAnimationRepeat(animation: Animation) {

                }
            })

            view.startAnimation(anim)
        }
    }

    fun slideDown(context: Context, view: View) {
        if(view.visibility == View.VISIBLE) {
            val anim = AnimationUtils.loadAnimation(context, R.anim.slid_down)
            anim.setFillAfter(false)

            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) { }

                override fun onAnimationEnd(animation: Animation) {
                    view.visibility = View.GONE
                }
                override fun onAnimationRepeat(animation: Animation) { }
            })

            view.startAnimation(anim)
        }
    }

}