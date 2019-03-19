package com.w3engineers.jitpackbottomnav

import android.content.BroadcastReceiver
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
import android.content.Intent
import android.content.IntentFilter
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.databinding.ActivityNavdrawerBinding
import com.w3engineers.jitpackbottomnav.fragment.chat.ChatViewModel
import com.w3engineers.jitpackbottomnav.fragment.home.HomeFragment
import com.w3engineers.jitpackbottomnav.fragment.home.HomeFragmentDirections


class MainActivity : AppCompatActivity(), NavController.OnNavigatedListener {

    private lateinit var navController: NavController
    private lateinit var navigation: BottomNavigationView
    private lateinit var currentFragment : Fragment
    private lateinit var mainViewModel: MainViewModel
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    fun currentFragment(fragment: Fragment){
        this.currentFragment = fragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNavdrawerBinding = DataBindingUtil.setContentView(this, R.layout.activity_navdrawer)

        navigation = findViewById(R.id.bottom_nav) as BottomNavigationView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.shop_nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(navigation, navHostFragment.navController)

        navController = navHostFragment.navController

        navigation.isItemHorizontalTranslationEnabled = true


        navController.addOnNavigatedListener(this)

        mainViewModel = getViewModel()

        subscribeViewMOdel()
        setSupportActionBar(binding.toolbar)
        drawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        /**
         * This will select the given id fragment
         */
        //navigation.selectedItemId = R.id.setting_fragment

        //navigation.inflateMenu(R.menu.menu_home)

    }

    private fun subscribeViewMOdel(){
        mainViewModel.navigateToDetails.observe(this, Observer {
            Toast.makeText(this,"Path "+it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        // val intent = intent
        if (intent.hasExtra("user")) {
            val user = intent.getParcelableExtra<User>("user")
            Log.e("Intent_value"," user name ="+user.userName)
            navController.navigate(HomeFragmentDirections.openChatPage(user))
            intent.removeExtra("user")
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        // navController.navigateUp() || super.onSupportNavigateUp()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigated(controller: NavController, destination: NavDestination) {
        val title = destination.label
        Log.e("Item_list", "destination " + title)
        /*supportActionBar!!.title = title
        if (title!!.equals("Chat") || title!!.equals("Profile image")) {
            toggleBottomView(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }else if(title!!.equals("Camera")){
            supportActionBar!!.hide()
            toggleBottomView(false)
            return
        }
        else {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            toggleBottomView(true)
        }
        supportActionBar!!.show()*/

    }

    private fun toggleBottomView(needToShow: Boolean) {
        if (needToShow) {
            AnimUtil.slideUp(this, navigation)
        } else {
            AnimUtil.slideDown(this, navigation)
        }
        hideKeyboard()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            popFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        popFragment()
    }


    fun popFragment() {
        val fragmentManager = supportFragmentManager.fragments
        if (fragmentManager.size > 0) {
            Log.e("Item_list", "Pop fragments")
            //supportFragmentManager.popBackStack()
            if(currentFragment is HomeFragment){

            }
        }
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    fun getMainViewModel() : MainViewModel = mainViewModel


    fun publishEvent(){

    }


}
