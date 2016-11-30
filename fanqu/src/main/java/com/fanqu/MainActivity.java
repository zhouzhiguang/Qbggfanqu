package com.fanqu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fanqu.framework.activities.BaseActivity;
import com.kymjs.okhttp.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.http.RequestQueue;
import com.kymjs.rxvolley.rx.Result;
import com.kymjs.rxvolley.toolbox.FileUtils;
import com.kymjs.rxvolley.toolbox.Loger;
import com.squareup.okhttp.OkHttpClient;

import java.io.UnsupportedEncodingException;

import cn.hugeterry.updatefun.UpdateFunGO;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    private Button button;

    private TextView mTextView;
    private Subscription subscription;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.test);
        button = (Button) findViewById(R.id.start);
//            TextView viewById = (TextView) findViewById(R.id.test);
        //传入参数 cacheFolder缓存文件
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(RxVolley.CACHE_FOLDER,
                new OkHttpStack(new OkHttpClient())));
        button.setOnClickListener(this);
        UpdateFunGO.init(this);

        Toast.makeText(this, "测试提交", Toast.LENGTH_SHORT).show();
        TabLayout tabLayout;
    }


    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }


    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                test();
//                download();
//                Loger.setEnable(true);
                //               startActivity(new Intent(this,ImageLoadTestActivity.class));
                break;
            default:
                break;
        }

    }


    /**
     * 下载
     */
    private void download() {

        RxVolley.download(FileUtils.getSDCardPath() + "/a.apk",
                "https://www.oschina.net/uploads/osc-android-app-2.4.apk", new ProgressListener() {
                    @Override
                    public void onProgress(long transferredBytes, long totalSize) {
                        Loger.debug(transferredBytes + "======" + totalSize);
                    }
                }, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        Loger.debug("====success" + t);
                        Toast.makeText(MainActivity.this, t, Toast.LENGTH_SHORT).show();
                        mTextView.setText("结果 onSuccess" + t.length());

                    }

                    @Override
                    public void onFailure(int errorNo, String strMsg) {
                        Loger.debug(errorNo + "====failure" + strMsg);

                        mTextView.setText(errorNo + "错误id" + "错误信息结果 failure" + strMsg);
                    }
                });
    }

    private void test() {
        Observable<Result> observable = new RxVolley.Builder()
                .url("http://kymjs.com/feed.xmlsss")
//                .url("https://api.douban.com/v2/book/26692621") //服务器端声明了no-cache
                .contentType(RxVolley.ContentType.FORM)
                .shouldCache(true)
                .httpMethod(RxVolley.Method.GET)
                .getResult();

        subscription = observable
                .filter(new Func1<Result, Boolean>() {
                    @Override
                    public Boolean call(Result result) {
                        return result.data != null;
                    }
                })
                .map(new Func1<Result, String>() {
                    @Override
                    public String call(Result result) {
                        byte[] res = result.data;
                        String srt2 = null;
                        try {
                            srt2 = new String(res, "utf-8");

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        return srt2;
                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i("kymjs", "======网络请求结束");
                    }

                    @Override

                    public void onError(Throwable e) {
                        Log.i("kymjs", "======网络请求失败" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("kymjs", "======网络请求" + s);
                        mTextView.setText("结果" + s);
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();


    }
}
