package com.example.jokingApp.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.jokingApp.BaseApplication;
import com.example.jokingApp.injector.component.DaggerServiceComponent;
import com.example.jokingApp.injector.moduel.ServiceModule;
import com.example.jokingApp.utils.helper.NetWorkHelper;
import com.example.jokingApp.utils.helper.SettingPrefHelper;
import com.example.jokingApp.utils.helper.UserStorage;

import org.apache.log4j.Logger;

import javax.inject.Inject;

/**
 * Created by idea-pc on 2016/4/13.
 */
public class MessageService extends Service {

    Logger logger = Logger.getLogger(MessageService.class.getSimpleName());

    public static final String ACTION_GET = "com.example.jokingApp.ACTION_GET";
    public static final String ACTION_UPDATE = "com.example.jokingApp.ACTION_UPDATE";
    public static final String ACTION_CLOSE = "com.example.jokingApp.ACTION_CLOSE";

    @Inject
    UserStorage mUserStorage;
//    @Inject
//    ForumApi mForumApi;
    @Inject
    SettingPrefHelper mSettingPrefHelper;
    @Inject
    NetWorkHelper mNetWorkHelper;


    private NotificationManager notificationManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        logger.debug("服务初始化");
        //注入
        DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this))
                .applicationComponent(((BaseApplication) getApplication()).getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //短路或   如果用户没有登录 或者 用户设置了不显示通知  那么停止该服务
        if (!mUserStorage.isLogin() || !mSettingPrefHelper.getNotification()) {
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
        //用户已经登录 并且 用户设置显示通知  那么执行以下代码
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        String action = intent != null ? intent.getAction() : "";

        if (ACTION_GET.equals(action)) {
        //    resetTheTime();
        //    loadMessage();
        } else if (ACTION_UPDATE.equals(action)) {
            logger.debug("刷新时间");
        //    resetTheTime();
        } else if (ACTION_CLOSE.equals(action)) {
        //    clearAlarm();
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);
    }

//    private void clearAlarm() {
//        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//        am.cancel(getOperation());
//    }

//    private void resetTheTime() {
//        logger.debug("resetTheTime");
//
//        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        // 指定时间
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.MINUTE, 10);
//        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), getOperation());
//    }

//    private PendingIntent getOperation() {
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        intent.setAction(ACTION_GET);
//        PendingIntent sender = PendingIntent.getService(getBaseContext()
//                , 1000, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        return sender;
//    }

//    private void loadMessage() {
//        if (!mNetWorkHelper.isWiFi()) {
//            return;
//        }
//        mForumApi.getMessageList("", 1).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<MessageData>() {
//            @Override
//            public void call(MessageData messageData) {
//                if (messageData != null && messageData.status == 200) {
//                    notifyMessageCount(messageData.result);
//                }
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//
//            }
//        });
    }


//    public void notifyMessageCount(MessageResult result) {
//        for (Message message : result.list) {
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//            builder.setSmallIcon(R.drawable.ic_list_comment).setContentTitle(message.info).setContentText("来自TLint");
//            Intent intent;
//            if (AppManager.getAppManager().isAppExit()) {
//                intent = new Intent(this, SplashActivity.class);
//                intent.setAction(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setAction(SplashActivity.ACTION_NOTIFICATION_MESSAGE);
//            } else {
//                intent = new Intent(this, MessageListActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
//            PendingIntent contentIntent = PendingIntent.getActivity(this, Integer.valueOf(message.id), intent, PendingIntent.FLAG_CANCEL_CURRENT);
//            builder.setContentIntent(contentIntent).setAutoCancel(true);
//            builder.setTicker(message.info);
//
//            notificationManager.notify(Integer.valueOf(message.id), builder.build());
//        }
//
//    }




