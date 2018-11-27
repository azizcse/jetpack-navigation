package com.w3engineers.jitpackbottomnav.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.w3engineers.jitpackbottomnav.R
import kotlinx.android.synthetic.main.fragment_exanple.view.*


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 11/26/2018 at 4:33 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 11/26/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

class ProfileImageFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_example2, container, false)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_profile_img, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.share_menu->{
                Toast.makeText(activity,"Share menu", Toast.LENGTH_SHORT).show()
                return true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}