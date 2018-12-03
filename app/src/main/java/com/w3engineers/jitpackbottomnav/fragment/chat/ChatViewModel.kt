package com.w3engineers.jitpackbottomnav.fragment.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.w3engineers.jitpackbottomnav.App
import com.w3engineers.jitpackbottomnav.data.model.Message
import com.w3engineers.jitpackbottomnav.data.model.Message_
import com.w3engineers.jitpackbottomnav.data.model.User
import io.objectbox.android.ObjectBoxDataSource
import io.objectbox.query.QueryBuilder
import java.util.*


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/30/2018 at 3:49 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/30/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class ChatViewModel : ViewModel() {
    val messageBox = App.getBoxStore().boxFor(Message::class.java)

    fun getMessagePagedLiveData(user : User):LiveData<PagedList<Message>>{
        val query = messageBox.query()
            .equal(Message_.friendsId, user.userId)
            .order(Message_.time, QueryBuilder.DESCENDING)
            .build()

        return LivePagedListBuilder<Int, Message>( ObjectBoxDataSource.Factory<Message>(query),10).build()
    }

    fun saveMessage(msg : String, user : User){
        val message = Message()
        message.friendsId = user.userId
        message.message = msg
        message.messageId = UUID.randomUUID().toString()
        message.user.target = user
        message.incoming = true

        messageBox.put(message) // Incoming message insert

        val outMessage = Message()
        outMessage.messageId = UUID.randomUUID().toString()
        outMessage.incoming = false
        outMessage.message = msg
        outMessage.friendsId = user.userId
        outMessage.user.target = user

        messageBox.put(outMessage) // Outgoing message insert
    }

    fun deleteAllMessage(user: User) {
        messageBox.query()
            .equal(Message_.friendsId, user.userId)
            .build()
            .remove()

    }
}