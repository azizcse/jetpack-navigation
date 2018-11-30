package com.w3engineers.jitpackbottomnav.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.core.kbasekit.ui.base.ItemClickListener
import com.w3engineers.jitpackbottomnav.R
import com.w3engineers.jitpackbottomnav.data.model.User


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

class HomeFragment : Fragment(), View.OnClickListener, ItemClickListener<User> {


    lateinit var button: Button
    lateinit var homeViewModel: HomeViewModel
    lateinit var recyclerVuew: RecyclerView
    //    lateinit var userAdapter: UserAdapter
    lateinit var pagedAdapter: UserPagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        button = view.findViewById(R.id.open_profile_page)
        recyclerVuew = view.findViewById(R.id.user_rv)
        //userAdapter = UserAdapter()
        //recyclerVuew.adapter = userAdapter

        pagedAdapter = UserPagedListAdapter(activity, this)
        recyclerVuew.adapter = pagedAdapter

        recyclerVuew.layoutManager = LinearLayoutManager(activity)
        recyclerVuew.setHasFixedSize(true);

        //userAdapter.setClickLisener(this)

        button.setOnClickListener(this)
        homeViewModel = getViewModel()
        loadData()
        return view
    }

    fun loadData() {
        homeViewModel.getPagedUserLiveData().observe(activity!!,
            Observer<PagedList<User>> {
                Log.e("Item_list", "List size =" + it.size)
                pagedAdapter.submitList(it)
                recyclerVuew.smoothScrollToPosition(pagedAdapter.itemCount - 1)
            })
    }


    fun getViewModel(): HomeViewModel {
        return ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)
    }

    override fun onClick(view: View?) {
        homeViewModel.saveUser("Name " + System.currentTimeMillis())
    }

    override fun onItemClick(view: View, item: User) {
        when (view.id) {
            R.id.delete_button -> {
                homeViewModel.deleteItem(item)
            }
            else -> {
                val bundle = Bundle()
                bundle.putParcelable("user", item)
                Navigation.findNavController(view!!).navigate(R.id.open_profile, bundle)
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.home_menu -> {
                Toast.makeText(activity, "Send menu", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}