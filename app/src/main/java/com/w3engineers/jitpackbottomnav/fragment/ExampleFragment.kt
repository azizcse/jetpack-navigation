package com.w3engineers.jitpackbottomnav.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

class ExampleFragment: Fragment(), View.OnClickListener {
    lateinit var openButton : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exanple, container, false)

        openButton = view.findViewById(R.id.open_fragment_example2)
        openButton.setOnClickListener(this)
        return view
    }

    override fun onClick(p0: View?) {
        Navigation.findNavController(view!!).navigate(R.id.open_fragment_example_second)
    }
}