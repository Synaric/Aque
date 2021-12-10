package com.synaric.aque.page.adapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kunminx.binding_recyclerview.adapter.SimpleDataBindingAdapter
import com.synaric.aque.R
import com.synaric.aque.data.entity.Note
import com.synaric.aque.databinding.ItemNoteBinding

class NoteListAAdapter(context: Context?) :
    SimpleDataBindingAdapter<Note, ItemNoteBinding>(
        context,
        R.layout.item_note,
        object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

        }) {

    override fun onBindItem(
        binding: ItemNoteBinding?,
        item: Note?,
        holder: RecyclerView.ViewHolder?
    ) {
        binding?.item = item
    }
}