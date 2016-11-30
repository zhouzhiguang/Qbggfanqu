package com.fanqu;


import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * 接口
 * Created by Hal on 15/4/26.
 */
public class ApiManager {

    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5";

    /**
     * 服务接口
     */
    private interface ApiManagerService {
        @GET("/weather")
        WeatherData getWeather(@Query("q") String place, @Query("units") String units);

        /**
         * retrofit 支持 rxjava 整合
         * 这种方法适用于新接口
         */
        @retrofit2.http.GET("/weather")
        Observable<WeatherData> getWeatherData(@Query("q") String place, @Query("units") String units);
    }
    private static final  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .build();
    //private static final retrofit2. restAdapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();

    private static final ApiManagerService apiManager = retrofit.create(ApiManagerService.class);

    /**
     * 将服务接口返回的数据，封装成{@link rx.Observable}
     * 这种写法适用于将旧代码封装
     * @param city
     * @return
     */
    public static Observable<WeatherData> getWeatherData(final String city) {
        return Observable.create(new Observable.OnSubscribe<WeatherData>() {
            @Override
            public void call(Subscriber<? super WeatherData> subscriber) {
                //订阅者回调 onNext 和 onCompleted
                subscriber.onNext(apiManager.getWeather(city, "metric"));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }
}
