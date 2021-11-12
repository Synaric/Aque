package com.synaric.aque.page;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.synaric.aque.BR;
import com.synaric.aque.R;
import com.synaric.aque.vm.HomeViewModel;
import com.synaric.architecture.ui.page.BaseFragment;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
public class HomeFragment extends BaseFragment {

    private HomeViewModel state;

    @Override
    protected void initViewModel() {
        state = getFragmentScopeViewModel(HomeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm, state)
                .addBindingParam(BR.click, state.new ClickProxy());
    }
}
