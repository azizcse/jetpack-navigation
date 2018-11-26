package com.w3engineers.jitpackbottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.bottom_nav) as BottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.shop_nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(navigation, navHostFragment.navController)

        navController = navHostFragment.navController

        navController.addOnNavigatedListener { _, destination ->

            val title = destination.label//
            supportActionBar!!.title = destination.label

            if(title!!.equals("Example") || title!!.equals("Example2")){
                toggleBottomView(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            }else{
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                toggleBottomView(true)
            }
        }

    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            toggleBottomView(true)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun toggleBottomView(needToShow: Boolean) {
        if(needToShow){
            navigation.visibility = View.VISIBLE
        }else{
            navigation.visibility = View.GONE
        }
    }
}
