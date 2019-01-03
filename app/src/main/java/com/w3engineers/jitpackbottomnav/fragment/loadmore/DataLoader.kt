package com.w3engineers.jitpackbottomnav.fragment.loadmore

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


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

abstract class DataLoader {
    private var currentPageNumber: Int = 0
    private var isLoading = false
    private var canLoadMore = true
    private var scrollListener: InfinityScrollListener;
    private var totalPageCount: Int = 0
    private var currentPage: Int = 0

    init {
        scrollListener = InfinityScrollListener()
    }

    fun setTotalPageNumber(totalPage : Int){
       totalPageCount = totalPage
    }


    fun getScrollListener(): RecyclerView.OnScrollListener = scrollListener

    fun markCurrentPageLoaded() {
        isLoading = false
        if (!canLoadMore) {
            completeLoading()
        }
    }

    abstract fun onLoadNextPage(pageNo: Int)

    private fun loadNextPage() {
        isLoading = true
        onLoadNextPage(currentPage++)
        if (currentPage == totalPageCount) {
            canLoadMore = false
        }
        Log.e("Next_page", "Next page called");
    }

    private fun completeLoading() {
        canLoadMore = false
    }


    private inner class InfinityScrollListener : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val totalItemCount = recyclerView.layoutManager?.getItemCount()
            var lastVisibleItemPosition = 0
            if (dy > 0 && !isLoading && canLoadMore) {

                if (recyclerView.layoutManager is LinearLayoutManager) {
                    lastVisibleItemPosition =
                            (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                } else if (recyclerView.layoutManager is GridLayoutManager) {
                    lastVisibleItemPosition =
                            (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                } else if (recyclerView.layoutManager is StaggeredGridLayoutManager) {
                    val lastVisibleItemPositions =
                        (recyclerView.layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                    lastVisibleItemPosition = lastVisibleItemPositions[lastVisibleItemPositions.size - 1]
                }

            }
            if (lastVisibleItemPosition + 1 >= totalItemCount!!) {
                loadNextPage()
            }
        }

    }

}