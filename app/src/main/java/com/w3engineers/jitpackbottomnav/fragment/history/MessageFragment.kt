package com.w3engineers.jitpackbottomnav.fragment.history

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.databinding.FragmentMessageBinding
import org.workfort.base.ui.base.BaseFragment


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/19/2018 at 5:47 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class MessageFragment : BaseFragment() {

    lateinit var binding: FragmentMessageBinding
    lateinit var messageViewModel: HistoryViewModel

    override val getLayoutId: Int
        get() = R.layout.fragment_message
    override val getMenuId: Int
        get() = R.menu.menu_message

    override fun startView() {
        binding = getViewBinding() as FragmentMessageBinding
        messageViewModel = HistoryViewModel()

        val userList = messageViewModel.getUserLiveData()

        for(user : User in userList) {
            Log.e("History_", "List size =" + user.lastMessage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.message_menu -> {
                Toast.makeText(activity, "Send menu", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun stopView() {

    }
}