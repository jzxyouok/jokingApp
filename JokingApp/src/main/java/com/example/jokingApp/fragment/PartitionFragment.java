package com.example.jokingApp.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.PartitionAdapter;
import com.example.jokingApp.bean.PartitionInfo;
import com.example.jokingApp.protocol.PartitionProtocol;
import com.example.jokingApp.view.LoadingPage;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class PartitionFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<PartitionInfo.PictureBean> mPictureBeen;

    @Override
    public View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_partition, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new PartitionAdapter(mPictureBeen));

        return view;
    }

    @Override
    public LoadingPage.LoadResult load() {
        PartitionProtocol partitionProtocol = new PartitionProtocol();
        mPictureBeen = (List<PartitionInfo.PictureBean>) partitionProtocol.load(0);
        return checkData(mPictureBeen);
    }
}

