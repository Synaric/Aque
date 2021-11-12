package com.synaric.aque.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public String content;

    public String category;

    public Long createTime;

    public Long updateTime;

    @NonNull
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
