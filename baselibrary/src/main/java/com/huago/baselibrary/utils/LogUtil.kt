package com.huago.baselibrary.utils

import android.util.Log
import com.huago.baselibrary.DevMode

/**
 * @author xjiang
 * @updateDes 2020/8/6
 */
class LogUtil {
    companion object {

        var TAG: String = LogUtil::class.java.simpleName

        fun d(msg: Any) {
            if (DevMode)
                Log.d(TAG, "$msg")
        }

        fun e(msg: Any) {
            if (DevMode)
                Log.e(TAG, "$msg")
        }


    }
}