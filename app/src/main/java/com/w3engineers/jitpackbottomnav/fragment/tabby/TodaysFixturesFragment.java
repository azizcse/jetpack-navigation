package com.w3engineers.jitpackbottomnav.fragment.tabby;

import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.core.kbasekit.ui.base.ItemClickListener;
import com.w3engineers.jitpackbottomnav.R;
import com.w3engineers.jitpackbottomnav.data.model.User;
import com.w3engineers.jitpackbottomnav.databinding.FragmentTodaysFixtureBinding;
import com.w3engineers.jitpackbottomnav.fragment.home.HomeViewModel;
import com.w3engineers.jitpackbottomnav.fragment.home.UserPagedListAdapter;
import org.jetbrains.annotations.NotNull;
import org.workfort.base.ui.base.BaseFragment;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [26-Dec-2018 at 12:19 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: jetpack-navigation.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [26-Dec-2018 at 12:19 PM].
 * * --> <Second Editor> on [26-Dec-2018 at 12:19 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [26-Dec-2018 at 12:19 PM].
 * * --> <Second Reviewer> on [26-Dec-2018 at 12:19 PM].
 * * ============================================================================
 **/

public class TodaysFixturesFragment extends BaseFragment implements ItemClickListener<User> {

    FragmentTodaysFixtureBinding binding;

    HomeViewModel homeViewModel;

    UserPagedListAdapter pagedAdapter;

    @Override
    public int getGetLayoutId() {
        return R.layout.fragment_todays_fixture;
    }

    @Override
    public int getGetMenuId() {
        return 0;
    }

    @Override
    public void startView() {

        binding = (FragmentTodaysFixtureBinding) getViewBinding();

        homeViewModel = getViewModel();
        initRecyclerView();
        loadData();
    }

    private void loadData() {
        homeViewModel.getPagedUserLiveData().observe(this,
                new Observer<PagedList<User>>() {
                    @Override
                    public void onChanged(PagedList<User> users) {
                        Log.e("Item_list", "User List size =" + users.size());
                        pagedAdapter.submitList(users);
                    }
                });
    }

    private void initRecyclerView() {
        pagedAdapter = new UserPagedListAdapter(getContext(), this);
        binding.recyclerViewAllFixtures.setAdapter(pagedAdapter);
        binding.recyclerViewAllFixtures.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewAllFixtures.setHasFixedSize(true);
        // binding.openProfilePage.setOnClickListener(this)
    }

    private HomeViewModel getViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void stopView() {

    }

    @NotNull
    @Override
    public Fragment currentFragment() {
        return this;
    }

    @Override
    public void onItemClick(@NotNull View view, User item) {

    }
}
