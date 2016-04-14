package com.example.jokingApp.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 *通过RXjava来替代Event Bus
 * Created by idea-pc on 2016/4/14.
 */
public class RxBus {

    private final Subject bus;

    //首先创建一个可同时充当Observer和Observable的Subject；
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * @param o
     */
    public void send(Object o) {
        bus.onNext(o);
    }

    /**
     * 这个方法直接用来接收 特定类型的数据
     * bus.ofType(eventType); ofType = filter + cast
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    /**
     * 如果仅仅是通知的话 可以使用此方法
     *
     * @param <T>
     * @return
     */

    //示例
    //_rxBus.send(new TapEvent());
    //    _rxBus.toObserverable()
    //            .subscribe(new Action1<Object>() {
    //        @Override
    //        public void call(Object event) {
    //
    //            if(event instanceof TapEvent) {
    //                _showTapText();
    //
    //            }else if(event instanceof SomeOtherEvent) {
    //                _doSomethingElse();
    //            }
    //        }
    //    });
    public <T> Observable<T> toObserverable() {
        return bus;
    }
}
