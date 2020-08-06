package com.huago.jetpackmvvm;

import com.huago.baselibrary.BaseApplication;
import com.lzy.okgo.OkGo;

/**
 * @author xjiang
 * @updateDes 2020/8/5
 */
public class HGApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }
}
