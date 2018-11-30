package com.w3engineers.jitpackbottomnav.fragment.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.w3engineers.jitpackbottomnav.App
import com.w3engineers.jitpackbottomnav.data.model.User
import io.objectbox.Box
import io.objectbox.android.ObjectBoxLiveData
import java.util.*

class HomeViewModel : ViewModel() {

    val userBox: Box<User> = App.getBoxStore().boxFor(User::class.java)

    fun getUserLiveData(): ObjectBoxLiveData<User> {
        return ObjectBoxLiveData<User>(userBox.query().build())
    }

    fun saveUser(name : String){
        val userId = UUID.randomUUID().toString()
        val user = User()
        user.userId = userId
        user.userName = name
        userBox.put(user)
    }
}