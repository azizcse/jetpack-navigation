package com.w3engineers.jitpackbottomnav.fragment.chat

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.User


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

class ChatFragment : Fragment(), View.OnClickListener {

    lateinit var sendButton : ImageButton
    lateinit var inputText : EditText
    lateinit var chatViewModel: ChatViewModel
    lateinit var recyclerView : RecyclerView
    lateinit var chatAdapter : ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        inputText = view.findViewById(R.id.edittext_message_input)
        sendButton = view.findViewById(R.id.image_button_send)
        recyclerView = view.findViewById(R.id.recycler_view_message)
        chatAdapter = ChatAdapter(activity)

        chatViewModel = getViewModel()

        val name = arguments?.get("user") as User

        return view
    }

    fun getViewModel(): ChatViewModel{
        return ViewModelProviders.of(activity!!).get(ChatViewModel::class.java)
    }





    override fun onClick(p0: View?) {
        //Navigation.findNavController(view!!).navigate(R.id.open_fragment_example_second)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_menu -> {
                Toast.makeText(activity, "Delete menu", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}