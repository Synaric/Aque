package com.synaric.aque.page;

import android.os.Bundle;
import android.view.View;

import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.synaric.aque.BR;
import com.synaric.aque.R;
import com.synaric.aque.page.adapter.NoteListAAdapter;
import com.synaric.aque.vm.HomeViewModel;
import com.synaric.architecture.ui.page.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
public class HomeFragment extends BaseFragment {

    private HomeViewModel state;

    private NoteListAAdapter adapter;

    @Override
    protected void initViewModel() {
        state = getFragmentScopeViewModel(HomeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        adapter = new NoteListAAdapter(getContext());
        adapter.setOnItemClickListener(state.new OnNoteListItemClick());
        return new DataBindingConfig(R.layout.fragment_home, BR.vm, state)
                .addBindingParam(BR.click, state.new ClickProxy())
                .addBindingParam(BR.adapter, adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state.getAllNote();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        state.noteList.observe(getViewLifecycleOwner(), notes -> state.noteList.notifyDataSetChanged(adapter));
    }
}
