package com.w3engineers.jitpackbottomnav.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.kbasekit.ui.base.ItemClickListener
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.User
import com.w3engineers.jitpackbottomnav.databinding.FragmentHomeBinding
import org.workfort.base.ui.base.BaseFragment


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

class HomeFragment : BaseFragment(), ItemClickListener<User> {

    lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var pagedAdapter: UserPagedListAdapter

    override val getLayoutId: Int
        get() = R.layout.fragment_home
    override val getMenuId: Int
        get() = R.menu.menu_home

    override fun startView() {
        binding = getViewBinding() as FragmentHomeBinding
        homeViewModel = getViewModel()
        initRecyclerView()
        loadData()
    }
    override fun currentFragment(): Fragment = this

    fun initRecyclerView() {
        pagedAdapter = UserPagedListAdapter(activity, this)
        binding.userRv.adapter = pagedAdapter
        binding.userRv.layoutManager = LinearLayoutManager(activity)
        binding.userRv.setHasFixedSize(true)
       // binding.openProfilePage.setOnClickListener(this)
    }

    fun loadData() {
        homeViewModel.getPagedUserLiveData().observe(this,
            Observer<PagedList<User>> {
                Log.e("Item_list", "User List size =" + it.size)
                pagedAdapter.submitList(it)

            })
    }


    fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onClick(view: View?) {
        homeViewModel.saveUser("Click to chat " + System.currentTimeMillis())
    }

    override fun onItemClick(view: View, item: User) {
        when (view.id) {
            R.id.delete_button -> {
                homeViewModel.deleteItem(item)
            }
            else -> {
                findNavController().navigate(HomeFragmentDirections.openExpandRecyclerView(item))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.add_user_menu -> {
                homeViewModel.saveUser("Click to chat " + System.currentTimeMillis())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun stopView() {

    }
}