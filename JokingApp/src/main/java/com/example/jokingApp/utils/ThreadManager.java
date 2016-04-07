package com.example.jokingApp.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的简单封装
 * 管理线程池
 * Created by idea-pc on 2016/3/17.
 */
public class ThreadManager {

    private ThreadPoolProxy mLongPool;
    private ThreadPoolProxy mShortPool;
    //单例
    private ThreadManager() {

    }

    private static ThreadManager instance = new ThreadManager();

    public static ThreadManager getInstance() {
        return instance;
    }

    /**
     * 读取本地文件  或者读取数据库的操作可以在这里进行
     *但是现在有了 RxJava  这个类是不是可以废弃了呢...
     * @return
     */
    public synchronized ThreadPoolProxy createShortPool() {
        if (mLongPool == null) {
            mLongPool = new ThreadPoolProxy(3, 3, 5000L);
        }
        return mLongPool;
    }

    /**
     * 请求网络
     * @return
     */
    public synchronized ThreadPoolProxy createLongPool() {
        if (mShortPool == null) {
            mShortPool = new ThreadPoolProxy(5, 5, 5000L);
        }
        return mShortPool;
    }


     public  class ThreadPoolProxy {
        private ThreadPoolExecutor mExecutor;
        private int corePoolSize;
        private int maximumPoolSize;
        private long time;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long time) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.time = time;
        }

        public void execute(Runnable runnable) {
            if (mExecutor == null) {
                //如果线程池为null的话 创建线程池
                /**
                 * 1 线程池里管理的线程数量
                 * 2  如果排队满了  额外开的线程
                 * 3  如果线程池里没有执行的任务 存货多久
                 * 4  时间的单位
                 * 5 如果线程池里管理的线程用完了  剩下的线程临时存到linkedblockingqueue中
                 */
                mExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, time, TimeUnit.MILLISECONDS, new
                        LinkedBlockingQueue<Runnable>(10));
            }
            //调用线程池去执行异步任务
            mExecutor.execute(runnable);
        }

        public void cancel(Runnable runnable) {
            if (mExecutor != null && mExecutor.isShutdown() && mExecutor.isTerminated()) {
                mExecutor.remove(runnable);
            }
        }
    }


}
