package com.fanqu.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.fanqu.PluginMethod.WxpayResultListener;
import com.fanqu.qbgg.fanqu.framework.Constants;
import com.fanqu.qbgg.fanqu.framework.activities.BaseActivity;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivity implements
		IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;
	private WxpayResultListener listener;

	public void setListener(WxpayResultListener listener) {
		this.listener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.pay_result);
        //System.out.println("微信支付成功后回調WXPayEntryActivity");
		api = WXAPIFactory.createWXAPI(this, Constants.WXAPI_PAY_APPID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {

	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			Intent intent = new Intent(Constants.WXPAY_SUCCESS_RESULT);
			intent.putExtra(Constants.WXPAY_SUCCESS_RESULT_CODE, resp.errCode);
			sendBroadcast(intent);
		}
	}

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return 0;
	}
}