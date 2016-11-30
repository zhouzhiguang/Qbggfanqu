package com.qbgg.cenglaicengqu.main.date;

import com.qbgg.cenglaicengqu.homepage.fragment.TestBean;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BeanRequestProtocol;
import com.yolanda.nohttp.RequestMethod;

import rx.Observable;

/**
 * 所有数据请求的集中管理者 包阔网络请求结果
 * 也是创建事件总线的管理中心
 */

public class DataManager {

    private BeanRequestProtocol beanRequestProtocol;

    public DataManager(BeanRequestProtocol beanRequestProtocol) {
        this.beanRequestProtocol = beanRequestProtocol;
    }

    /**
     *
     * @param <T>
     * @return
     * 获取当前登录用户的信息
     */
    public <T> Observable<T> buildRequestBean() {
        RequestBean bean = new RequestBean();
        bean.setUrl("http://api.nohttp.net/test");
        bean.setRequestMethod(RequestMethod.POST);
        bean.setClazz(TestBean.class);
        bean.setCacheMode(NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE);
        return beanRequestProtocol.createObservable(bean.getUrl(), bean.getParams(), bean.getRequestMethod(), bean.getCacheMode(), bean.getClazz());
    }
}
