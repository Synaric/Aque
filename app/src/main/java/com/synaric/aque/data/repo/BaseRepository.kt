package com.synaric.aque.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Synaric at 2021/11/10 0010.
 */
open class BaseRepository {

    suspend fun <T> executeSQL(block: () -> T): T{
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }
}