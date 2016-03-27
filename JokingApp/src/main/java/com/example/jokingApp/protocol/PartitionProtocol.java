package com.example.jokingApp.protocol;

import com.example.jokingApp.bean.PartitionInfo;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/24.
 */
public class PartitionProtocol extends  BaseProtocol {
    @Override
    public String getKey() {
        return "partition";
    }

    @Override
    public Object parseJson(String json) {
        Gson gson = new Gson();
        PartitionInfo partitionInfo = gson.fromJson(json, PartitionInfo.class);
        List<PartitionInfo.PictureBean> picture = partitionInfo.getPicture();
        return picture;
    }
}
