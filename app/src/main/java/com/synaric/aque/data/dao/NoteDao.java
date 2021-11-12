package com.synaric.aque.data.dao;

import com.synaric.aque.data.entity.Note;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("select * from note order by createTime desc")
    List<Note> queryAll();
}
