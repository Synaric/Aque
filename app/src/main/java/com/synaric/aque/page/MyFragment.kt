package com.synaric.aque.page

import com.synaric.architecture.ui.page.BaseFragment
import com.synaric.aque.vm.MyViewModel
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.synaric.aque.BR
import com.synaric.aque.R

/**
 * Created by Synaric at 2021/11/10 0010.
 */
class MyFragment : BaseFragment() {
    private lateinit var state: MyViewModel

    override fun initViewModel() {
        state = getFragmentScopeViewModel(MyViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_my, BR.vm, state)
    }
}