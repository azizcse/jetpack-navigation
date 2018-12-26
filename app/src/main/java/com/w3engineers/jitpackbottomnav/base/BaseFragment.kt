package org.workfort.base.ui.base

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.w3engineers.jitpackbottomnav.MainActivity
import com.w3engineers.jitpackbottomnav.base.BaseActivity
import java.lang.RuntimeException


/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 10/10/2018 at 1:41 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Purpose:
*  *
*  * Last edited by : Md. Azizul Islam on 10/10/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/

abstract class BaseFragment : Fragment(), View.OnClickListener {

    abstract val getLayoutId: Int
    abstract val getMenuId: Int
    private val DEFAULT_VALUE = 0


    private lateinit var viewBinding: ViewDataBinding

    //abstract method
    abstract fun startView()

    abstract fun stopView()
    abstract fun currentFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getMenuId > DEFAULT_VALUE)
            setHasOptionsMenu(true)
    }


    fun getViewBinding(): ViewDataBinding {
        return viewBinding
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutId > DEFAULT_VALUE) {
            viewBinding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        } else throw RuntimeException("Please set layout with databinding")

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startView()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (getMenuId > DEFAULT_VALUE) {
            inflater!!.inflate(getMenuId, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()

        if (activity != null && activity is BaseActivity) {

            (activity as BaseActivity).currentFragment = currentFragment()
        }
    }

    override fun onClick(view: View?) {}
}