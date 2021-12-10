package com.synaric.architecture.utils

import com.synaric.architecture.livedata.PagingLiveData
import java.util.ArrayList

/**
 * 维护分页状态工具类。
 * Created by Synaric at 2021/11/12 0012.
 */
class PagingUtils {
    class PagingModel(length: Int) {

        var page = 0

        var length = 10

        private var isLast = false

        fun <T> fetch(list: PagingLiveData<MutableList<T>?>, result: List<T>?, isRefresh: Boolean) {
            if (isRefresh) {
                page = 0
                isLast = false
            }
            if (isLast) {
                return
            }
            if (result == null) {
                return
            }
            var dataList = list.value
            if (dataList == null) {
                dataList = ArrayList()
            }
            dataList.addAll(result)
            isLast = result.size < length
            page++
            list.setValue(
                dataList,
                PagingLiveData.NotifyDataSetChangedInfo.ofInsertRange((page - 1) * length, length)
            )
        }

        fun <T> fetch(list: PagingLiveData<MutableList<T>?>, result: List<T>?) {
            fetch(list, result, false)
        }

        init {
            this.length = length
        }
    }
}