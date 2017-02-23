package com.fanqu.framework.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2017/2/23.
 */

public class RxBus {
    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());  //线程安全

    public void send(Object o) {
        _bus.onNext(o);
    }

    /**获取实际的Bus对象*/
    public Observable<Object> toObserverable() {

        return _bus;
    }

    /**是否含有观察者*/
    public boolean hasObservers() {
        return _bus.hasObservers();
    }
}
