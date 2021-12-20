package com.synaric.aque.vm

import com.kunminx.binding_recyclerview.adapter.BaseDataBindingAdapter
import com.synaric.aque.data.entity.Note
import com.synaric.aque.data.repo.NoteRepository
import com.synaric.architecture.livedata.PagingLiveData
import com.synaric.architecture.utils.PagingUtils.PagingModel
import com.synaric.architecture.viewmodel.BaseViewModel
import java.util.*

/**
 * Created by Synaric at 2021/11/10 0010.
 */
class HomeViewModel : BaseViewModel() {

    val noteList = PagingLiveData<MutableList<Note>?>()

    private val noteRepository = NoteRepository.instance
    private val noteListPaging = PagingModel(10)

    fun getAllNote() {
        launchUI {
            val dataResult = noteRepository.queryAll(noteListPaging.page, noteListPaging.length)
            noteListPaging.fetch(
                noteList,
                dataResult
            )
        }
    }

    fun saveNote() {
        val note = Note()
        note.title = "标题"
        note.content = "内容" + if (noteList.value != null) noteList.value!!.size else "0"
        note.category = "未分类"
        note.createTime = Date().time
        note.updateTime = note.createTime

        launchUI {
            noteRepository.insertNote(note)
            val noteListValue: MutableList<Note>? = noteList.value as MutableList<Note>?
            if (noteListValue != null) {
                noteListValue.add(0, note)
                noteList.setValue(
                    noteListValue,
                    PagingLiveData.NotifyDataSetChangedInfo.ofInsert(0)
                )
            }
        }
    }

    inner class OnNoteListItemClick : BaseDataBindingAdapter.OnItemClickListener<Note> {
        override fun onItemClick(viewId: Int, item: Note, position: Int) {
            val noteListValue = noteList.value
            if (noteListValue != null) {
                item.content = item.content + "~"
                noteList.setValue(
                    noteList.value,
                    PagingLiveData.NotifyDataSetChangedInfo.ofUpdate(position)
                )
            }
        }
    }
}