package com.synaric.architecture.utils;

import com.synaric.architecture.livedata.PagingLiveData;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * 维护分页状态工具类。
 * Created by Synaric at 2021/11/12 0012.
 */
public class PagingUtils {

    public static class PagingModel {

        public int page = 0;

        public int length;

        private boolean isLast;

        public PagingModel(int length) {
            this.length = length;
        }

        public <T> void fetch(PagingLiveData<List<T>> list, List<T> result, boolean isRefresh) {
            if (isRefresh) {
                page = 0;
                isLast = false;
            }
            if (isLast) {
                return;
            }
            if (result == null) {
                return;
            }
            List<T> dataList = list.getValue();
            if (dataList == null) {
                dataList = new ArrayList<>();
            }
            dataList.addAll(result);
            isLast = result.size() < length;
            page++;
            list.setValue(dataList, PagingLiveData.NotifyDataSetChangedInfo.ofInsertRange((page - 1) * length, length));
        }

        public <T> void fetch(PagingLiveData<List<T>> list, List<T> result) {
            fetch(list, result, false);
        }
    }
}
