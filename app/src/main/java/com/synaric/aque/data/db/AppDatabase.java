package com.synaric.aque.data.db;

import android.content.Context;

import com.synaric.aque.data.dao.NoteDao;
import com.synaric.aque.data.entity.Note;
import com.synaric.architecture.BaseApplication;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(BaseApplication.context, AppDatabase.class, "app.db").build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract NoteDao noteDao();
}
