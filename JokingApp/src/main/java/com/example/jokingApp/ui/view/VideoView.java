package com.example.jokingApp.ui.view;

import com.example.jokingApp.bean.MeizhiInfo;

import java.util.List;

/**
 * Created by idea-pc on 2016/4/20.
 */
public interface VideoView extends  BaseView {

      void setAdapterData(List<MeizhiInfo.ResultsBean> mResults);
}
