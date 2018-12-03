package com.w3engineers.jitpackbottomnav.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.w3engineers.jitpackbottomnav.App
import com.w3engineers.jitpackbottomnav.data.model.User
import io.objectbox.Box
import io.objectbox.android.ObjectBoxLiveData
import java.util.*
import androidx.paging.LivePagedListBuilder
import com.w3engineers.jitpackbottomnav.data.model.Message
import com.w3engineers.jitpackbottomnav.data.model.Message_
import com.w3engineers.jitpackbottomnav.data.model.User_
import io.objectbox.android.ObjectBoxDataSource


class HomeViewModel : ViewModel() {

    val userBox: Box<User> = App.getBoxStore().boxFor(User::class.java)
    val messageBox = App.getBoxStore().boxFor(Message::class.java)

    fun getPagedUserLiveData(): LiveData<PagedList<User>>{
        val query = userBox.query().order(User_.userName).build()
        return LivePagedListBuilder<Int, User>( ObjectBoxDataSource.Factory<User>(query),20).build();
    }

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

    fun deleteItem(item: User) {
        userBox.remove(item)
    }
}