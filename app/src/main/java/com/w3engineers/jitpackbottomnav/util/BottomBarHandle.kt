package com.w3engineers.jitpackbottomnav.util

import androidx.recyclerview.widget.RecyclerView
import com.w3engineers.jitpackbottomnav.MainActivity


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 12/12/2018 at 5:35 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 12/12/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class BottomBarHandle internal constructor(val mainActivity: MainActivity) : RecyclerView.OnScrollListener() {

    var scrollCallTime = 0L

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        super.onScrolled(recyclerView, dx, dy)

        if (Math.abs(System.currentTimeMillis() - scrollCallTime) < 500) return
        scrollCallTime = System.currentTimeMillis()
        if (dy > 0) {
            mainActivity.toggleBottomView(false)
            hideToolbar()
        } else {
            mainActivity.toggleBottomView(true)
            showToolbar()
        }
    }


    fun showToolbar(){
        if(!mainActivity.supportActionBar!!.isShowing){
            mainActivity.supportActionBar!!.show()
        }
    }

    fun hideToolbar(){
        if(mainActivity.supportActionBar!!.isShowing){
            mainActivity.supportActionBar!!.hide()
        }
    }
}