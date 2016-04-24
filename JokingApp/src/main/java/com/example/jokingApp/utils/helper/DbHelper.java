package com.example.jokingApp.utils.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jokingApp.db.DaoMaster;
import com.example.jokingApp.db.DaoSession;
import com.example.jokingApp.db.JokeEntityDao;
import com.example.jokingApp.utils.UiUtils;

/**
 * 数据库帮助类
 * Created by idea-pc on 2016/4/21.
 */

public class DbHelper {

    private Context context;
    private final DaoSession mSession;

    public DbHelper( ) {
        this.context = UiUtils.getContext();
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"dao.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取daomaster
        DaoMaster daoMaster = new DaoMaster(db);
        //创建一个会话
        mSession = daoMaster.newSession();
    }

    /**
     * /拿到Joke表
     * @return
     */
    public  JokeEntityDao  getJokeEntityDao(){
        JokeEntityDao jokeEntityDao = mSession.getJokeEntityDao();
        return jokeEntityDao ;
    }
}
