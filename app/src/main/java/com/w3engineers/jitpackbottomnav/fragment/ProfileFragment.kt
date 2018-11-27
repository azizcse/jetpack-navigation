package com.w3engineers.jitpackbottomnav.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.w3engineers.jitpackbottomnav.R


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

class ProfileFragment: Fragment(), View.OnClickListener {
    lateinit var openButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exanple, container, false)

        val name = arguments?.get("name") as String


        openButton = view.findViewById(R.id.open_fragment_example2)
        openButton.setOnClickListener(this)

        Toast.makeText(activity, name, Toast.LENGTH_LONG).show()

        return view
    }

    override fun onClick(p0: View?) {
        Navigation.findNavController(view!!).navigate(R.id.open_fragment_example_second)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.serch_menu -> {
                Toast.makeText(activity,"Search menu", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.send_menu->{
                Toast.makeText(activity,"Send menu", Toast.LENGTH_SHORT).show()
                return true
            } else -> super.onOptionsItemSelected(item)
        }
    }

}