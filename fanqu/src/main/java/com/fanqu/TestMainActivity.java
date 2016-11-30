package com.fanqu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestMainActivity extends AppCompatActivity {
    private static final String[] CITIES = {"Budapest,hu"};
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main_layout);
        Observable.from(CITIES).flatMap(new Func1<String, Observable<WeatherData>>() {
            @Override
            public Observable<WeatherData> call(String s) {
                return ApiManager.getWeatherData(s);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(/*onNext*/new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        Log.d(LOG_TAG, weatherData.toString());
                    }
                }, /*onError*/new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

        /**
         * 单个 city 请求
         */
        ApiManager.getWeatherData(CITIES[0]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        Log.d(LOG_TAG, weatherData.toString());
                        ((TextView) findViewById(R.id.text)).setText(weatherData.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(LOG_TAG, throwable.getMessage(), throwable);
                    }
                });


//        /**
//         * Android View 事件处理
//         */
//
//        .clicks(findViewById(R.id.text))
//                .throttleFirst(500, TimeUnit.MILLISECONDS)
//                .subscribe(new Action1<Void>() {
//
//                    @Override
//                    public void call(Void aVoid) {
//                        Toast.makeText(getApplicationContext(), "Progress", Toast.LENGTH_SHORT).show();//主线程
//                    }
//                });
//
//        Observable.just("a", "b")
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
//                        Toast.makeText(getApplicationContext(), "Progress", Toast.LENGTH_SHORT).show();//主线程
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())//指定doOnSubscribe在主线程
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.i(LOG_TAG, "Thread id: " + Thread.currentThread().getId());
//                    }
//                });
//

    }


}
