package com.w3engineers.jitpackbottomnav.fragment.loadmore

import androidx.recyclerview.widget.RecyclerView


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 1/1/2019 at 7:00 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 1/1/2019.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class DataLoader {
    private var currentPageNumber : Int = 0
    private var isLoading = false
    private var canLoadMore = false


    private inner class InfinityScrollListener : RecyclerView.OnScrollListener(){

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val totalItemCount = recyclerView.layoutManager?.getItemCount()

            if(dy > 0 && !isLoading && canLoadMore){

            }
        }

    }

}