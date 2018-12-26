package com.w3engineers.jitpackbottomnav.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.w3engineers.jitpackbottomnav.util.AnimUtil


/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [26-Dec-2018 at 1:46 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: jetpack-navigation.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [26-Dec-2018 at 1:46 PM].
 * * --> <Second Editor> on [26-Dec-2018 at 1:46 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [26-Dec-2018 at 1:46 PM].
 * * --> <Second Reviewer> on [26-Dec-2018 at 1:46 PM].
 * * ============================================================================
 **/

abstract class BaseActivity : AppCompatActivity() {

    abstract val getItemIdToHide: View

    lateinit var currentFragment: Fragment


    fun toggleBottomView(needToShow: Boolean) {

        if (needToShow) {
            AnimUtil.slideUp(this, getItemIdToHide)
        } else {
            AnimUtil.slideDown(this, getItemIdToHide)
        }

        hideKeyboard()
    }

    fun hideKeyboard() {

        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (currentFocus != null) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}