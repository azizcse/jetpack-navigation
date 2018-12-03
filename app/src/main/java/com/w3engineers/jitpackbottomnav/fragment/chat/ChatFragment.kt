package com.w3engineers.jitpackbottomnav.fragment.chat

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.Message
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.databinding.FragmentChatBinding
import org.workfort.base.ui.base.BaseFragment


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/26/2018 at 3:52 PM.
*  * Email : azizul@w3engineers.com
*  *
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/26/2018.
*  *
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
*  ****************************************************************************
*/

class ChatFragment : BaseFragment() {

    lateinit var binding: FragmentChatBinding
    lateinit var chatAdapter: ChatAdapter
    lateinit var user: User
    lateinit var chatViewModel: ChatViewModel

    override val getLayoutId: Int
        get() = R.layout.fragment_chat
    override val getMenuId: Int
        get() = R.menu.menu_profile

    override fun startView() {
        binding = getViewBinding() as FragmentChatBinding
        chatAdapter = ChatAdapter(activity)
        binding.imageButtonSend.setOnClickListener(this)
        binding.recyclerViewMessage.adapter = chatAdapter
        binding.recyclerViewMessage.layoutManager = LinearLayoutManager(activity)
        chatViewModel = getViewModel()
        user = arguments?.get("user") as User
        loadData()
    }

    fun getViewModel(): ChatViewModel {
        return ViewModelProviders.of(this).get(ChatViewModel::class.java)
    }

    private fun loadData() {
        chatViewModel.getMessagePagedLiveData(user).observe(this,
            Observer<PagedList<Message>> {
                Log.e("Item_list", "Chat List size =" + it.size)
                chatAdapter.submitList(it)
                //recyclerView.scrollToPosition(chatAdapter.itemCount-1)
            })
    }


    override fun onClick(p0: View?) {
        //Navigation.findNavController(view!!).navigate(R.id.open_fragment_example_second)
        val value = binding.edittextMessageInput.text.toString().trim()
        if (value.isEmpty()) return
        chatViewModel.saveMessage(value, user)
        binding.edittextMessageInput.setText("")

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_menu -> {
                Toast.makeText(activity, "Message deleted", Toast.LENGTH_SHORT).show()
                chatViewModel.deleteAllMessage(user)
                return true
            }
            R.id.profile_menu->{
                Navigation.findNavController(view!!).navigate(R.id.open_fragment_example_second)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun stopView() {

    }


}