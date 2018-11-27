package com.w3engineers.jitpackbottomnav.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.w3engineers.jitpackbottomnav.R


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/19/2018 at 5:23 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        button = view.findViewById(R.id.open_profile_page);
        button.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View?) {
        /* val options = NavOptions.Builder()
             .setEnterAnim(R.anim.slide_in_right)
             .setExitAnim(R.anim.slide_out_left)
             .setPopEnterAnim(R.anim.slide_in_left)
             .setPopExitAnim(R.anim.slide_out_right)
             .build()*/

        val bundle = Bundle()
        bundle.putString("name", "User profile")
        Navigation.findNavController(view!!).navigate(R.id.open_profile, bundle)
        //val url = "http://192.168.2.57:8000/telemesh/survey/?format=api
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.home_menu->{
                Toast.makeText(activity,"Send menu", Toast.LENGTH_SHORT).show()
                return true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}