package com.synaric.aque.data.repo

import com.synaric.aque.data.repo.BaseRepository
import com.synaric.aque.data.db.AppDatabase
import com.synaric.aque.data.entity.Note
import com.synaric.aque.data.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Synaric at 2021/11/10 0010.
 */
class NoteRepository private constructor() : BaseRepository() {

    suspend fun insertNote(note: Note): Long = executeSQL {
        AppDatabase.INSTANCE.noteDao().insert(note)
    }

    suspend fun queryAll(page: Int, length: Int): MutableList<Note>  = executeSQL {
        AppDatabase.INSTANCE.noteDao().queryAll(page, length)
    }

    companion object {
        val instance = NoteRepository()
    }
}