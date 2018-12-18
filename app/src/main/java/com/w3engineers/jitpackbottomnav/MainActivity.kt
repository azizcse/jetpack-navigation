package com.w3engineers.jitpackbottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.w3engineers.jitpackbottomnav.util.AnimUtil
import android.content.Context
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity(), NavController.OnNavigatedListener {

    private lateinit var navController: NavController
    private lateinit var navigation: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.bottom_nav) as BottomNavigationView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.shop_nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(navigation, navHostFragment.navController)

        navController = navHostFragment.navController

        navigation.isItemHorizontalTranslationEnabled = true


        navController.addOnNavigatedListener(this)

        /**
         * This will select the given id fragment
         */
        //navigation.selectedItemId = R.id.setting_fragment

        //navigation.inflateMenu(R.menu.menu_home)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onNavigated(controller: NavController, destination: NavDestination) {
        val title = destination.label
        Log.e("Item_list", "destination "+title )
        supportActionBar!!.title = title
        if(title!!.equals("Chat") || title!!.equals("Profile image")){
            toggleBottomView(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }else{
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            toggleBottomView(true)
        }
    }

    private fun toggleBottomView(needToShow: Boolean) {
        if(needToShow){
            AnimUtil.slideUp(this,navigation)
        }else{
            AnimUtil.slideDown(this,navigation)
        }
        hideKeyboard()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            popFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        popFragment()
    }


    fun popFragment(){
        val fragmentManager = supportFragmentManager.fragments
        if(fragmentManager.size> 0){
            Log.e("Item_list", "Pop fragments" )
            //supportFragmentManager.popBackStack()
        }
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }


    }

}
