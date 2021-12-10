package com.synaric.aque.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.synaric.aque.data.entity.Note

/**
 * Created by Synaric at 2021/11/10 0010.
 */
@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note): Long

    @Query("select * from note order by createTime desc limit :page * :length, :length")
    fun queryAll(page: Int, length: Int): MutableList<Note>
}