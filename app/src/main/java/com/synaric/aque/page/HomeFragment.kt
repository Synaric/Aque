package com.synaric.aque.page

import android.os.Bundle
import android.view.View
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.synaric.aque.BR
import com.synaric.aque.R
import com.synaric.aque.page.adapter.NoteListAdapter
import com.synaric.aque.vm.HomeViewModel
import com.synaric.architecture.ui.page.BaseFragment

/**
 * Created by Synaric at 2021/11/10 0010.
 */
class HomeFragment : BaseFragment() {

    private lateinit var state: HomeViewModel
    private lateinit var adapter: NoteListAdapter

    override fun initViewModel() {
        state = getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        adapter = NoteListAdapter(context)
        adapter.setOnItemClickListener(state.OnNoteListItemClick())
        return DataBindingConfig(R.layout.fragment_home, BR.vm, state)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.adapter, adapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        state.getAllNote()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        state.noteList.observe(
            viewLifecycleOwner,
            { state.noteList.notifyDataSetChanged(adapter) })
    }

    inner class ClickProxy {
        fun saveNote() {
            state.saveNote()
//            nav().navigate(R.id.fragment_note_detail)
        }
    }
}