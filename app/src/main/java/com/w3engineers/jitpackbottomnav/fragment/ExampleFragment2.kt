package com.w3engineers.jitpackbottomnav.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ExampleFragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_example2, container, false)
        return view
    }
}