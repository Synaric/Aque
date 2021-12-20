package com.synaric.architecture.livedata;

import android.annotation.SuppressLint;

import java.util.LinkedList;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.ListAdapter;

public class PagingLiveData<List> extends MutableLiveData<List> {

    /**
     * 标识一次数据列表更新的类型。
     */
    public static class NotifyDataSetChangedInfo {
        public static final int DEFAULT = -1;
        public static final int INSERT = 0;
        public static final int UPDATE = 1;
        public static final int REMOVE = 2;
        public static final int INSERT_RANGE = 3;
        public static final int UPDATE_RANGE = 4;
        public static final int REMOVE_RANGE = 5;

        public int type = DEFAULT;
        public int positionStart = 0;
        public int itemCount = 0;

        private NotifyDataSetChangedInfo() {}

        public static NotifyDataSetChangedInfo ofInsert(int position) {
            NotifyDataSetChangedInfo info = new NotifyDataSetChangedInfo();
            info.type = INSERT;
            info.positionStart = position;
            return info;
        }

        public static NotifyDataSetChangedInfo ofUpdate(int position) {
            NotifyDataSetChangedInfo info = new NotifyDataSetChangedInfo();
            info.type = UPDATE;
            info.positionStart = position;
            return info;
        }

        public static NotifyDataSetChangedInfo ofInsertRange(int position, int itemCount) {
            NotifyDataSetChangedInfo info = new NotifyDataSetChangedInfo();
            info.type = INSERT_RANGE;
            info.positionStart = position;
            info.itemCount = itemCount;
            return info;
        }

        public static NotifyDataSetChangedInfo ofDefault() {
            NotifyDataSetChangedInfo info = new NotifyDataSetChangedInfo();
            info.type = DEFAULT;
            return info;
        }
    }

    private final LinkedList<NotifyDataSetChangedInfo> infoLinkedList = new LinkedList<>();

    public synchronized void setValue(List value, NotifyDataSetChangedInfo info) {
        infoLinkedList.push(info);
        super.setValue(value);
    }

    /**
     * 默认实现，更新数据列表时请使用更高效的{@link #setValue(Object, NotifyDataSetChangedInfo)}
     */
    @Override
    public void setValue(List value) {
        setValue(value, NotifyDataSetChangedInfo.ofDefault());
        super.setValue(value);
    }

    @SuppressLint("NotifyDataSetChanged")
    @SuppressWarnings("rawtypes")
    public synchronized void notifyDataSetChanged(ListAdapter adapter) {
        NotifyDataSetChangedInfo info = infoLinkedList.poll();
        if (info == null) {
            adapter.notifyDataSetChanged();
            return;
        }
        switch (info.type) {
            case NotifyDataSetChangedInfo.INSERT:
                int pos = info.positionStart;
                if (pos < 0) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.notifyItemInserted(info.positionStart);
                }
                break;
            case NotifyDataSetChangedInfo.UPDATE:
                adapter.notifyItemChanged(info.positionStart);
                break;
            case NotifyDataSetChangedInfo.INSERT_RANGE:
                adapter.notifyItemRangeInserted(info.positionStart, info.itemCount);
                break;
            default:
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
