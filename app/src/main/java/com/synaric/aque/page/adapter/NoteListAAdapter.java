package com.synaric.aque.page.adapter;

import android.content.Context;

import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter;
import com.synaric.aque.R;
import com.synaric.aque.data.entity.Note;
import com.synaric.aque.databinding.ItemNoteBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Synaric at 2021/11/12 0012.
 */
public class NoteListAAdapter extends SimpleDataBindingAdapter<Note, ItemNoteBinding> {

    public NoteListAAdapter(Context context) {
        super(context, R.layout.item_note, new DiffUtil.ItemCallback<Note>() {
            @Override
            public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
                return oldItem.id == newItem.id;
            }
        });
    }

    @Override
    protected void onBindItem(ItemNoteBinding binding, Note item, RecyclerView.ViewHolder holder) {
        binding.setItem(item);
    }
}
