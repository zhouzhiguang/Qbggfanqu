package com.fanqu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanqu.R;
import com.google.gson.Gson;
import com.kymjs.core.bitmap.client.BitmapCore;
import com.kymjs.okhttp.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.RequestQueue;
import com.kymjs.rxvolley.toolbox.Loger;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lib.homhomlib.design.SlidingLayout;
import me.iwf.photopicker.PhotoPicker;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;
import rx.subscriptions.CompositeSubscription;
import top.zibin.luban.Luban;
import uk.co.senab.photoview.PhotoView;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;


public class ImageLoadTestActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image1;
    private ImageView image;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private HttpCallback callback;
    private String imagurl = "http://b.hiphotos.baidu.com/image/h%3D200/sign=b54162e5c4bf6c81e8372be88c3fb1d7/d50735fae6cd7b89b792bc0e072442a7d8330e44.jpg";
    private String sdimage = Environment.getExternalStorageDirectory().getPath() + "/test/test.jpg";
    private Button button;
    private Looper backgroundLooper;
    private  final String TAG="ImageLoadTestActivity类";
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load_test);
        image = (ImageView) findViewById(R.id.image);
        image1 = (ImageView) findViewById(R.id.image1);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        Gson gson;
        // RxView rxView;
        PhotoView photoView;
        CompositeSubscription compositeSubscription;

        Luban luban;
//        PhotoViewAttacher mAttacher;
//        image2 = (ImageView) findViewById(R.id.image2);
//        image3 = (ImageView) findViewById(R.id.image3);
//        image4 = (ImageView) findViewById(R.id.image4);
//        image5 = (ImageView) findViewById(R.id.image5);

        setUp();
        testBitmapWithDiskLoader3();
        testBitmapWithDiskLoader();
        tearDown();
        image1.setOnClickListener(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        //  test();
        SlidingLayout slidingLayout;
    }

    private void tearDown() {
        //PhotoView photoView;

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();

    }

    public void testBitmapWithDiskLoader3() {
        File f = new File(sdimage);
        if (f.exists()) {
//        new BitmapCore.Builder()
//                .url(sdimage)
//                .callback(callback)
//                .view(image1)
//                .loadResId(R.mipmap.ic_launcher)
//                .errorResId(R.mipmap.ic_launcher)
//                .doTask();
            testBitmapWithDiskLoader2(sdimage);


        } else {

            Toast.makeText(this, "文件不存在" + sdimage + "**", Toast.LENGTH_SHORT).show();
        }
    }

    private void commit() {


        HttpParams params = new HttpParams();
        params.put("name[]", "hello");
        params.put("name[]", "hello2");
        params.put("name[]", "hello3");
        params.put("name2", "hello3333");
        RxVolley.post("http://192.168.31.155/php/array_info.php", params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Loger.debug("=======::" + t);
            }

        });
    }

    public void testBitmapWithDiskLoader2(String path) {
        new BitmapCore.Builder()
                .url(path)
                .callback(callback)
                .view(image1)
                .loadResId(R.mipmap.ic_launcher)
                .errorResId(R.mipmap.ic_launcher)
                //并行访问本地图片
                .useAsyncLoadDiskImage(true)
                .doTask();
    }

    private void testBitmapWithDiskLoader() {
        new BitmapCore.Builder()
                .url(imagurl)
                .callback(callback)
                .view(image)
                .loadResId(R.mipmap.ic_launcher)
                .errorResId(R.mipmap.ic_launcher)
                .doTask();
    }

    public void test() {
        new BitmapCore.Builder()
                .view(image1)
                .url("http://jingyan.baidu.com/album/6c67b1d6f4f8d62787bb1eaf.html?picindex=1")
                .callback(callback).doTask();
    }

    private void setUp() {
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(RxVolley.CACHE_FOLDER,
                new OkHttpStack(new OkHttpClient())));

        callback = new HttpCallback() {
            @Override
            public void onPreStart() {
                Loger.debug("=====onPreStart");
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            //访问网络之前会回调
            @Override
            public void onPreHttp() {
                super.onPreHttp();
                Loger.debug("=====onPreHttp");
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            //仅在Bitmap回调有效
            @Override
            public void onSuccess(Map<String, String> headers, Bitmap bitmap) {
                super.onSuccess(headers, bitmap);
                Loger.debug("=====onSuccessBitmap" + headers.size());
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Loger.debug("=====onFailure" + strMsg);
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Loger.debug("=====onFinish");
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }
        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image1:
                shownImageselect();
//                Toast.makeText(this, "commit 提交", Toast.LENGTH_SHORT).show();
//                commit();
                break;
            case R.id.button:
                Toast.makeText(getApplicationContext(),"点击",Toast.LENGTH_SHORT).show();
                Log.v(TAG, "onError() 执行了 吗");
                onRunSchedulerExampleButtonClicked();
                break;
            case R.id.button2:
                startActivity(new Intent(getApplicationContext(),TestMainActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 测试Rxandroid
     */
    private void onRunSchedulerExampleButtonClicked() {
        Observable<String> observable = sampleObservable();
        observable.subscribeOn(AndroidSchedulers.from(backgroundLooper))
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                });

    }

    private Observable<String> sampleObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    // Do some long running operation
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    private void shownImageselect() {
        PhotoPicker.builder()
                .setPhotoCount(3)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(ImageLoadTestActivity.this, PhotoPicker.REQUEST_CODE);
    }
    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
