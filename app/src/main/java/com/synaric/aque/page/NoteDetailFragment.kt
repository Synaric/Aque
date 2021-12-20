package com.synaric.aque.page

import android.os.Bundle
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.synaric.aque.BR
import com.synaric.aque.R
import com.synaric.aque.vm.NoteDetailViewModel
import com.synaric.architecture.ui.page.BaseFragment

/**
 * Created by Synaric at 2021/11/10 0010.
 */
class NoteDetailFragment : BaseFragment() {
    private lateinit var state: NoteDetailViewModel

    override fun initViewModel() {
        state = getFragmentScopeViewModel(NoteDetailViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_note_detail, BR.vm, state)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}