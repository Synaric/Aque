package com.synaric.aque.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Synaric at 2021/11/10 0010.
 */
@Entity(tableName = "note")
class Note {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    var title: String? = null

    var content: String? = null

    var category: String? = null

    var coverUrl: String? = null

    var createTime: Long? = null

    var updateTime: Long? = null
}