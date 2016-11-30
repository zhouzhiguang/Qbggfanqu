package com.qbgg.network.request.update;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dou361.update.ParseData;
import com.dou361.update.UpdateHelper;
import com.dou361.update.bean.Update;
import com.dou361.update.type.RequestType;
import com.qbgg.network.request.R;
import com.qbgg.network.request.update.bean.CheckResultRepository;
import com.qbgg.network.request.update.bean.UpdateBean;

/**
 * 自动更新配置文件
 */

public class UpdateConfig {
    //更新地址
    private static String checkUrl = "http://api.nohttp.net/test";
    private static String onlineUrl = "你的在线参数接口";
    /**
     * 模拟返回json数据
     */
    private static String json_result = "{\"code\":0,\"data\":{\"download_url\":\"http://wap.apk.anzhi.com/data3/apk/201512/20/55089e385f6e9f350e6455f995ca3452_26503500.apk\",\"force\":false,\"update_content\":\"测试更新接口\",\"v_code\":\"10\",\"v_name\":\"v1.0.0.16070810\",\"v_sha1\":\"7db76e18ac92bb29ff0ef012abfe178a78477534\",\"v_size\":12365909}}";


    public static void init(final Context context) {
        UpdateHelper.init(context);
        UpdateHelper.getInstance()
                /**可填：请求方式*/
                .setMethod(RequestType.post)
                /**必填：数据更新接口，该方法一定要在setDialogLayout的前面,因为这方法里面做了重置DialogLayout的操作*/
                .setCheckUrl(checkUrl)
                /**可填：清除旧的自定义布局设置。之前有设置过自定义布局，建议这里调用下*/
                .setClearCustomLayoutSetting()
                .setDialogLayout(R.layout.update_dialog)
                .setStatusBarLayout(R.layout.custom_download_notification)
                //这个是强制更新参数界面
                .setDialogDownloadLayout(R.layout.custom_download_dialog)

                /**可填：自定义更新弹出的dialog的布局样式，主要案例中的布局样式里面的id为（jjdxm_update_content、jjdxm_update_id_ok、jjdxm_update_id_cancel）的view类型和id不能修改，其他的都可以修改或删除*/
//                .setDialogLayout(R.layout.custom_update_dialog)
                /**可填：自定义更新状态栏的布局样式，主要案例中的布局样式里面的id为（jjdxm_update_rich_notification_continue、jjdxm_update_rich_notification_cancel、jjdxm_update_title、jjdxm_update_progress_text、jjdxm_update_progress_bar）的view类型和id不能修改，其他的都可以修改或删除*/
//                .setStatusBarLayout(R.layout.custom_download_notification)
                /**可填：自定义强制更新弹出的下载进度的布局样式，主要案例中的布局样式里面的id为(jjdxm_update_iv_icon、jjdxm_update_progress_bar、jjdxm_update_progress_text)的view类型和id不能修改，其他的都可以修改或删除*/
//                .setDialogDownloadLayout(R.layout.custom_download_dialog)
                /**必填：用于从数据更新接口获取的数据response中。解析出Update实例。以便框架内部处理*/
                .setCheckJsonParser(new ParseData() {
                    @Override
                    public Update parse(String response) {
                        /**真实情况下使用的解析  response接口请求返回的数据*/
//                        CheckResultRepository checkResultRepository = JSON.parseObject(response,CheckResultRepository.class);
                        /**临时使用 此处模拟一个返回的json数据json_result*/

                        CheckResultRepository checkResultRepository = JSON.parseObject(json_result, CheckResultRepository.class);
                        UpdateBean updateBean = checkResultRepository.getData();
                        Update update = new Update();
                        /**必填：此apk包的下载地址*/
                        update.setUpdateUrl(updateBean.getDownload_url());
                        /**必填：此apk包的版本号*/
                        update.setVersionCode(updateBean.getV_code());
                        /**可填：此apk包的大小*/
                        update.setApkSize(updateBean.getV_size());
                        /**必填：此apk包的版本名称*/
                        update.setVersionName(updateBean.getV_name());
                        /**可填：此apk包的更新内容*/
                        update.setUpdateContent(updateBean.getUpdate_content());
                        /**可填：此apk包是否为强制更新*/
                        update.setForce(updateBean.isForce());
                        //update=new Update();
                        return update;
                    }
                });
    }
}