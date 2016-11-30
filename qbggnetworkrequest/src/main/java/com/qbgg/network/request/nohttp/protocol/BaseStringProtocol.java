package com.qbgg.network.request.nohttp.protocol;


import android.text.TextUtils;
import android.util.Log;

import com.qbgg.network.request.nohttp.NohttpClientFactory;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * 单单 请求网络返回
 */
public class BaseStringProtocol {

    private String result;

    public BaseStringProtocol() {

    }


    /**
     * 创建一个工作在IO线程的被观察者(被订阅者)对象
     *
     * @param url
     * @param method
     * @param params
     */
    public Observable<String> createObservable(final String url, final Map<String, String> params, final RequestMethod requestMethod, final CacheMode mode) {


        return Observable.create(new Observable.OnSubscribe<String>() {                         //  (2)
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //这里就是要请求了
                // Request<String> request = NohttpClientFactory.getClient().getRequest(url, params, requestMethod, mode);
//                request.
//                Request request = XgoHttpClient.getClient().getRequest(url, method, params);    //  (3)
//                String json = XgoHttpClient.getClient().execute2String(request);
                Request<String> stringRequest = NoHttp.createStringRequest(url, requestMethod);
                stringRequest.add(params);
                //stringRequest.setCacheKey("CacheKeyDefaultString");// 这里的key是缓存数据的主键，默认是url，使用的时候要保证全局唯一，否则会被其他相同url数据覆盖。
                stringRequest.setCacheMode(mode);//默认就是DEFAULT，所以这里可以不用设置，DEFAULT代表走Http标准协议。
                Response<String> response = NoHttp.startRequestSync(stringRequest);
                if (response.isSucceed()) {
                    result = response.get();
                    setData(subscriber, result);

                } else {
                    setData(subscriber, null);
                }
            }

        }).subscribeOn(Schedulers.io());
    }


    /**
     * 为观察者（订阅者）设置返回数据
     */
    protected void setData(Subscriber<? super String> subscriber, String json) {
        if (TextUtils.isEmpty(json)) {                          //  (6)
            subscriber.onError(new Throwable("not data"));
            return;
        }
        subscriber.onNext(json);                                //  (7)
        subscriber.onCompleted();
    }
}
