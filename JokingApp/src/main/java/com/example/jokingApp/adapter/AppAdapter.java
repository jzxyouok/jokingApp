package com.example.jokingApp.adapter;

import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.R;
import com.example.jokingApp.holder.AppHolder;
import com.example.jokingApp.holder.MoreHolder;
import com.example.jokingApp.utils.ThreadManager;
import com.example.jokingApp.utils.UiUtils;

import java.util.ArrayList;

/**
 * Created by idea-pc on 2016/3/21.
 */
public class AppAdapter extends RecyclerView.Adapter {
    public AppAdapter(ArrayList<String> strings) {
        mStrings = strings;
    }
    String[] mMore = new String[]{"2", "2", "2", "2", "2", "2", "2", "2", "2", "2"};

    private static final int MORE_ITEM = 1;
    private static final int DEFAULT_ITEM = 0;
    private MoreHolder mHolder;
    private ArrayList<String>  mStrings ;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == DEFAULT_ITEM) {
            View view = View.inflate(UiUtils.getContext(), R.layout.item_fragment_app, null);
            return new AppHolder(view);
        } else {
            View view = View.inflate(UiUtils.getContext(), R.layout.load_more, null);
            mHolder = new MoreHolder(view,this);
           return mHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == DEFAULT_ITEM) {
            AppHolder holder1 = (AppHolder) holder;
            holder1.mTextView.setText(mStrings.get(position));
        }
        if (itemViewType == MORE_ITEM) {
                mHolder.loadMore();
        }

    }

    @Override
    public int getItemCount() {
        return mStrings.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mStrings.size()) { // 当前是最后一个条目
            return MORE_ITEM;
        }
        return DEFAULT_ITEM;
    }

    public void loadMore() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(500);
                // 在子线程中加载更多
               // final List<Data> newData = onload();
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMore == null) {
                            mHolder.setData(MoreHolder.LOAD_ERROR);//
                        } else if (mMore.length == 0) {
                            mHolder.setData(MoreHolder.HAS_NO_MORE);
                        } else {
                            // 成功了
                            mHolder.setData(MoreHolder.HAS_MORE);
                            mStrings.add("4");
                            mStrings.add("5");
                            mStrings.add("6");
                            mStrings.add("7");
                            mStrings.add("8");
                            mStrings.add("9");
                            mStrings.add("10");
                            notifyDataSetChanged();// 刷新界面
                        }

                    }
                });
            }
        });
    }
}
