package com.qbgg.network.request.nohttp.protocol;

import com.qbgg.network.request.nohttp.nohttp.JavaBeanRequest;
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
 *请求 网络返回Java实体类
 */

public class BeanRequestProtocol {

    public BeanRequestProtocol() {

    }




    /**
     * @param url 请求地址
     * @param params 请求参数
     * @param requestMethod 请求方式
     * @param modeurl 请求 缓存模式
     * @param clazz  最后解析成的累i
     * @param <T> 泛行
     * @return 观察者
     */
    public <T> Observable<T>  createObservable(final String url, final Map<String, String> params, final RequestMethod requestMethod, final CacheMode modeurl,final Class<T> clazz) {


        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                Request request = new JavaBeanRequest(url, clazz,requestMethod);
                request.set(params);
                request.setCacheMode(modeurl);//默认就是DEFAULT，所以这里可以不用设置，DEFAULT代表走Http标准协议。
                Response<T> response = NoHttp.startRequestSync(request);
                 if (response.isSucceed()) {
                      T clazz = response.get();
                     setData(subscriber,clazz);
                 }else {
                     setData(subscriber, null );
                 }
            }
        }).subscribeOn(Schedulers.io());

    }

    /**
     * 为观察者（订阅者）设置返回数据
     */
    protected <T> void setData(Subscriber<? super T> subscriber,  T clazz) {
        if (clazz ==null) {
            subscriber.onError(new Throwable("not data"));
            return;
        }

        subscriber.onNext(clazz);
        subscriber.onCompleted();
    }
}
