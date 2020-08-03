package com.huago.jetpackmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fmt.github.base.viewmodel.BaseViewModel
import com.huago.jetpackmvvm.dao.DBManager
import com.huago.jetpackmvvm.dao.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

/**
 * @author xjiang
 * @updateDes 2020/7/31
 */
class MainViewModel : BaseViewModel() {
    val userInfos: MutableLiveData<MutableList<UserInfo>> = MutableLiveData()


    fun loadUserInfoList() {
        launch {

            val loadAll = DBManager.getInstance().userInfoDao?.loadAll()?.toMutableList()
            Log.e("debug", "loadAll:  " + Thread.currentThread().name)
            loadAll?.let {
                userInfos.postValue(it)
            }
        }
        Log.e("debug", "withContext(Dispatchers.IO): " + Thread.currentThread().name)
    }

    fun saveUserInfo(userInfo: UserInfo) {
        Log.e("debug", "saveUserInfo: $userInfo  " + Thread.currentThread().name)
        launch {
            DBManager.getInstance().daoSession.insert(userInfo)
            Log.e("debug", "saveUserInfo  launch: " + Thread.currentThread().name)

            loadUserInfoList()
        }
    }

    suspend fun get1(): String {
        Log.e("debug", "get1  delay ")
        delay(5000)
        return "5000"
    }

    suspend fun get2(): String {
        Log.e("debug", "get2  delay ")

        delay(2000)
        return "2000"
    }

    suspend fun get3(): String {
        Log.e("debug", "get3  delay ")

        delay(1)
        return "1"
    }

    fun merge() {
        launch {

            Log.e("debug", "merge: merge start")
            val async1 = GlobalScope.async {
                get1()
                99
            }
            val async2 = GlobalScope.async {
                get2()
            }
            val async3 = GlobalScope.async {
                get3()
            }
//            Log.e("debug", "result1: " + get1())
//            Log.e("debug", "result2: " + get2())
//            Log.e("debug", "result3: " + get3())

            Log.e("debug", "result1: " + async1.await())
            Log.e("debug", "result2: " + async2.await())
            Log.e("debug", "result3: " + async3.await())
        }

    }

}