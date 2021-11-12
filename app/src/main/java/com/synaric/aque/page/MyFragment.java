package com.synaric.aque.page;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.synaric.aque.BR;
import com.synaric.aque.R;
import com.synaric.aque.vm.MyViewModel;
import com.synaric.architecture.ui.page.BaseFragment;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
public class MyFragment extends BaseFragment {

    private MyViewModel state;

    @Override
    protected void initViewModel() {
        state = getFragmentScopeViewModel(MyViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return  new DataBindingConfig(R.layout.fragment_my, BR.vm, state);
    }
}
