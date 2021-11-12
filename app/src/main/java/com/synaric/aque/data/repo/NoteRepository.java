package com.synaric.aque.data.repo;

import com.synaric.aque.data.db.AppDatabase;
import com.synaric.aque.data.entity.Note;
import com.synaric.architecture.response.DataResult;
import com.synaric.architecture.response.ResultSource;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
public class NoteRepository extends BaseRepository {

    private static final NoteRepository INSTANCE = new NoteRepository();

    private NoteRepository() {
    }

    public static NoteRepository getInstance() {
        return INSTANCE;
    }

    public void insertNote(DataResult.Result<Boolean> result, Note note) {
        execute(() -> {
            AppDatabase.getInstance().noteDao().insert(note);
            return true;
        }, result);
    }

    public void queryAll(DataResult.Result<List<Note>> result) {
        execute(() -> AppDatabase.getInstance().noteDao().queryAll(), result);
    }
}
