package com.w3engineers.jitpackbottomnav.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.w3engineers.jitpackbottomnav.MainActivity
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.util.onUiThread
import org.jetbrains.anko.startActivity


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/29/2018 at 6:17 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/29/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        gotoNextPage()
    }

    private fun gotoNextPage(){
        onUiThread(1000,{
            startActivity<MainActivity>()
            overridePendingTransition(R.anim.slide_to_left, R.anim.slide_from_right)
            finish()
        })
    }
}