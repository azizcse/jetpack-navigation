package com.w3engineers.jitpackbottomnav.fragment.history

import android.util.Property
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.w3engineers.jitpackbottomnav.App
import com.w3engineers.jitpackbottomnav.data.model.Message
import com.w3engineers.jitpackbottomnav.data.model.Message_
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.data.model.User_
import io.objectbox.Box
import io.objectbox.android.ObjectBoxDataSource
import io.objectbox.android.ObjectBoxLiveData
import io.objectbox.query.Query
import io.objectbox.query.QueryBuilder
import io.objectbox.query.QueryFilter
import io.objectbox.rx.RxBoxStore
import io.objectbox.rx.RxQuery
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.function.Function


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 12/3/2018 at 3:41 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 12/3/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class HistoryViewModel : ViewModel() {
    val userBox: Box<User> = App.getBoxStore().boxFor(User::class.java)
    val messageBox = App.getBoxStore().boxFor(Message::class.java)
   // val message = messageBox.query().equal(Message_.friendsId, it.userId).build().find(1, 1)

    fun getUserLiveData():List<User> {
       val  users = userBox.query().build().find()
        val history = ArrayList<User>()
        for (user : User in users){
            val messsage = messageBox.query()
                .equal(Message_.friendsId, user.userId)
                .order(Message_.time, QueryBuilder.DESCENDING)
                .build()
                .find(1,1)

            if(messsage != null){
                user.lastMessage = messsage.get(0).message
                history.add(user)
            }
        }

        return history
    }

    fun rxuse(): Observable<List<User>> {
        val query = userBox.query().build()
        return RxQuery.observable(query).subscribeOn(Schedulers.io())
    }


}

