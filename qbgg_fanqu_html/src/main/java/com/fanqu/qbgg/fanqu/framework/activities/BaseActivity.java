package com.fanqu.qbgg.fanqu.framework.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.model.NaviLatLng;
import com.fanqu.qbgg.fanqu.framework.annotation.Injector;
import com.fanqu.qbgg.fanqu.framework.autolayout.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;


@SuppressLint("NewApi")
public class BaseActivity extends FragmentActivity {
    public static final String KEY_UUID = "uuid";

    private boolean mIsDestroyed = false;


    protected AMapNaviView mAMapNaviView;
    protected AMapNavi mAMapNavi;
    protected NaviLatLng mEndLatlng = new NaviLatLng(39.925846, 116.432765);
    protected NaviLatLng mStartLatlng = new NaviLatLng(39.925041, 116.437901);
    protected final List<NaviLatLng> sList = new ArrayList<NaviLatLng>();
    protected final List<NaviLatLng> eList = new ArrayList<NaviLatLng>();
    protected List<NaviLatLng> mWayPointList;
    //private TTSController mTtsManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        //	initthem();
//        mTtsManager = TTSController.getInstance(getApplicationContext());
//        mTtsManager.init();

        //

        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
       // mAMapNavi.addAMapNaviListener(this);
        // mAMapNavi.addAMapNaviListener(mTtsManager);

        //设置模拟导航的行车速度
        mAMapNavi.setEmulatorNaviSpeed(75);
        sList.add(mStartLatlng);
        eList.add(mEndLatlng);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
    }


    /**
     * 初始化沉浸式狀態欄
     */
    private void initthem() {
        // TODO Auto-generated method stub
//		/**
//		 * 沉浸式状态栏
//		 */
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			// 状态栏透明 需要在创建SystemBarTintManager 之前调用。
//			setTranslucentStatus(true);
//		}
//
//		SystemBarTintManager tintManager = new SystemBarTintManager(this);
//		tintManager.setStatusBarTintEnabled(true);
//		// 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。 设置沉浸式状态
//		tintManager.setStatusBarTintResource(0x000000000);
//		// tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.bg1));
//		tintManager.setStatusBarDarkMode(false, this);
//		// 设置状态栏的文字颜色
//		tintManager.setStatusBarDarkMode(false, this);
    }

    @Override
    public final void setContentView(int i) {
        super.setContentView(i);
        Injector.inject(this, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);

        setIntent(intent);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mAMapNaviView != null) {
            mAMapNaviView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAMapNaviView != null) {
            mAMapNaviView.onPause();
//        仅仅是停止你当前在说的这句话，一会到新的路口还是会再说的
            //  mTtsManager.stopSpeaking();
//
//        停止导航之后，会触及底层stop，然后就不会再有回调了，但是讯飞当前还是没有说完的半句话还是会说完
//        mAMapNavi.stopNavi();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAMapNaviView != null) {
            mAMapNaviView.onDestroy();
            //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();请自行执行
            mAMapNavi.stopNavi();
            mAMapNavi.destroy();
            //  mTtsManager.destroy();

            mAMapNaviView.onResume();
        }
    }

//    @Override
//    public void onInitNaviFailure() {
//        ToastUtils.showCenterToast(this, "onInitNaviFailure+++================");
//    }

    @Override
    public final boolean isDestroyed() {
        // TODO Auto-generated method stub
        return mIsDestroyed;
    }

    protected final void callbackForResult(int resultCode) {
        Intent data = new Intent();

        callbackForResult(resultCode, data);
    }

    protected final void callbackForResult(int resultCode, Intent data) {
        data.putExtra(KEY_UUID, getIntent().getStringExtra(KEY_UUID));

        setResult(resultCode, data);
    }

    protected int getLayoutId() {
        return 0;
    }

    ;

    protected void addFragment(int containerId, Fragment fragment) {
        if (fragment != null && !fragment.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.add(containerId, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    protected void replaceFragment(int containerId, Fragment fragment) {
        if (fragment != null && !fragment.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(containerId, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

//
//    @Override
//    public void onInitNaviSuccess() {
//        ToastUtils.showCenterToast(this, "onInitNaviSuccess+++================");
//    }
//
//    @Override
//    public void onStartNavi(int i) {
//
//    }
//
//    @Override
//    public void onTrafficStatusUpdate() {
//
//    }
//
//    @Override
//    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
//        ToastUtils.showCenterToast(this, "onLocationChange+++================");
//    }
//
//    @Override
//    public void onGetNavigationText(int i, String s) {
//
//    }
//
//    @Override
//    public void onEndEmulatorNavi() {
//
//    }
//
//    @Override
//    public void onArriveDestination() {
//
//    }
//
//    @Override
//    public void onArriveDestination(NaviStaticInfo naviStaticInfo) {
//
//    }
//
//    @Override
//    public void onArriveDestination(AMapNaviStaticInfo aMapNaviStaticInfo) {
//
//    }
//
//    @Override
//    public void onCalculateRouteSuccess() {
//
//    }
//
//    @Override
//    public void onCalculateRouteFailure(int errorInfo) {
//        //路线计算失败
//        LogUtil.e("dm", "--------------------------------------------");
//        LogUtil.i("dm", "路线计算失败：错误码=" + errorInfo + ",Error Message= " + ErrorInfo.getError(errorInfo));
//        LogUtil.i("dm", "错误码详细链接见：http://lbs.amap.com/api/android-navi-sdk/guide/tools/errorcode/");
//        LogUtil.e("dm", "--------------------------------------------");
//        ToastUtils.showCenterToast(BaseActivity.this, "errorInfo：" + errorInfo + ",Message：" + ErrorInfo.getError(errorInfo));
//    }
//
//    @Override
//    public void onReCalculateRouteForYaw() {
//
//    }
//
//    @Override
//    public void onReCalculateRouteForTrafficJam() {
//
//    }
//
//    @Override
//    public void onArrivedWayPoint(int i) {
//
//    }
//
//    @Override
//    public void onGpsOpenStatus(boolean b) {
//
//    }
//
//    @Override
//    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {
//
//    }
//
//    @Override
//    public void onNaviInfoUpdate(NaviInfo naviInfo) {
//
//    }
//
//    @Override
//    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
//
//    }
//
//    @Override
//    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
//
//    }
//
//    @Override
//    public void showCross(AMapNaviCross aMapNaviCross) {
//
//    }
//
//    @Override
//    public void hideCross() {
//
//    }
//
//    @Override
//    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {
//
//    }
//
//    @Override
//    public void hideLaneInfo() {
//
//    }
//
//    @Override
//    public void onCalculateMultipleRoutesSuccess(int[] ints) {
//
//    }
//
//    @Override
//    public void notifyParallelRoad(int i) {
//
//    }
//
//    @Override
//    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
//
//    }
//
//    @Override
//    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
//
//    }
//
//    @Override
//    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
//
//    }
//
//    @Override
//    public void onNaviSetting() {
//
//    }
//
//    @Override
//    public void onNaviCancel() {
//
//    }
//
//    @Override
//    public boolean onNaviBackClick() {
//        return false;
//    }
//
//    @Override
//    public void onNaviMapMode(int i) {
//
//    }
//
//    @Override
//    public void onNaviTurnClick() {
//
//    }
//
//    @Override
//    public void onNextRoadClick() {
//
//    }
//
//    @Override
//    public void onScanViewButtonClick() {
//
//    }
//
//    @Override
//    public void onLockMap(boolean b) {
//
//    }
//
//    @Override
//    public void onNaviViewLoaded() {
//
//    }
}
