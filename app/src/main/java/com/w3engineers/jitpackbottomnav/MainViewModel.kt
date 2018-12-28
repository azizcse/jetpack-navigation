package com.w3engineers.jitpackbottomnav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 12/28/2018 at 4:58 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 12/28/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class MainViewModel : ViewModel() {
    private val _navigateToDetails = MutableLiveData<String>()

    val navigateToDetails: LiveData<String>
        get() = _navigateToDetails


    fun capturedImagePath(path: String) {
        _navigateToDetails.value = path
    }

    fun navigateToDetailsHandled() {
        _navigateToDetails.value = "Hello from"
    }
}