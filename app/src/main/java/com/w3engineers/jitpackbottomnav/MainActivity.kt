package com.w3engineers.jitpackbottomnav

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnimationUtils.loadAnimation
import com.w3engineers.jitpackbottomnav.util.AnimUtil


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

        navController.addOnNavigatedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onNavigated(controller: NavController, destination: NavDestination) {
        val title = destination.label
        supportActionBar!!.title = title
        if(title!!.equals("Profile") || title!!.equals("Profile image")){
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
    }

}
