package com.synaric.aque.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.synaric.aque.data.dao.NoteDao
import com.synaric.aque.data.db.AppDatabase
import androidx.room.Room
import com.synaric.aque.data.entity.Note
import com.synaric.architecture.BaseApplication

/**
 * Created by Synaric at 2021/11/10 0010.
 */
@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        var INSTANCE: AppDatabase = Room.databaseBuilder(
            BaseApplication.context,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }
}