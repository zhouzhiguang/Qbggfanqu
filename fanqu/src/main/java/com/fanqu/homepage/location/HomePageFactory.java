package com.fanqu.homepage.location;

import com.fanqu.main.location.BaseUrlFactory;

/**
 * Created by Administrator on 2017/2/23.
 */

public class HomePageFactory extends BaseUrlFactory {



    public static String getHomepageDateurl() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/index/index");
        return builder.toString();
    }
}
