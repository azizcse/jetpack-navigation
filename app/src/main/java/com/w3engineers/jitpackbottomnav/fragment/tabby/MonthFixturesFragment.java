package com.w3engineers.jitpackbottomnav.fragment.tabby;

import androidx.fragment.app.Fragment;
import com.w3engineers.jitpackbottomnav.R;
import com.w3engineers.jitpackbottomnav.databinding.FragmentProfileBinding;
import org.jetbrains.annotations.NotNull;
import org.workfort.base.ui.base.BaseFragment;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Sudipta K Paik on [26-Dec-2018 at 12:20 PM].
 * * Email: sudipta@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: jetpack-navigation.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [26-Dec-2018 at 12:20 PM].
 * * --> <Second Editor> on [26-Dec-2018 at 12:20 PM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [26-Dec-2018 at 12:20 PM].
 * * --> <Second Reviewer> on [26-Dec-2018 at 12:20 PM].
 * * ============================================================================
 **/

public class MonthFixturesFragment extends BaseFragment {
    FragmentProfileBinding binding;

    @Override
    public int getGetLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public int getGetMenuId() {
        return 0;
    }

    @Override
    public void startView() {

        binding = (FragmentProfileBinding) getViewBinding();

        binding.textViewTitle.setText("Month Fixtures Fragment");
    }


    @Override
    public void stopView() {

    }

    @NotNull
    @Override
    public Fragment currentFragment() {
        return this;
    }
}
