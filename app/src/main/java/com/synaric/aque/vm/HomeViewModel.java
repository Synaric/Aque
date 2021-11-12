package com.synaric.aque.vm;

import com.synaric.aque.data.entity.Note;
import com.synaric.aque.data.repo.NoteRepository;
import com.synaric.architecture.utils.ToastUtils;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
public class HomeViewModel extends ViewModel {

    private final NoteRepository noteRepository = NoteRepository.getInstance();

    public final MutableLiveData<List<Note>> noteList = new MutableLiveData<>();

    public void getAllNote() {
        noteRepository.queryAll(dataResult -> {
            noteList.setValue(dataResult.getResult());
        });
    }

    public class ClickProxy {

        public void saveNote() {
            Note note = new Note();
            note.title = "标题";
            note.content = "内容" + (noteList.getValue() != null ? noteList.getValue().size() : "0");
            note.category = "未分类";
            note.createTime = new Date().getTime();
            note.updateTime = note.createTime;
            noteRepository.insertNote((r) -> {
                List<Note> noteListValue = HomeViewModel.this.noteList.getValue();
                if (noteListValue != null) {
                    noteListValue.add(0, note);
                    noteList.setValue(noteListValue);
                }

            }, note);
        }

        public void getAllNote() {
            noteRepository.queryAll((r) -> {
                ToastUtils.showShortToast(r.getResult().get(0).toString());
            });
        }
    }
}
