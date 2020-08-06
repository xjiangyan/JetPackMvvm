package com.huago.jetpackmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fmt.github.base.viewmodel.BaseViewModel
import com.fmt.github.base.viewmodel.Cancel
import com.huago.jetpackmvvm.dao.DBManager
import com.huago.jetpackmvvm.dao.UserInfo
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author xjiang
 * @updateDes 2020/7/31
 */
class MainViewModel : BaseViewModel() {
    val userInfos: MutableLiveData<MutableList<UserInfo>> = MutableLiveData()


    fun loadUserInfoList() {
        launch(context = Dispatchers.IO) {
            for (i in 0..100) {

                val loadAll = DBManager.getInstance().userInfoDao?.loadAll()?.toMutableList()
                Log.e("debug", "loadAll:  " + Thread.currentThread().name)
                loadAll?.let {
                    userInfos.postValue(it)
                }
            }
//        var da: LiveData<Int> = emit {
//            1
//        }
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
//        launch {
//
//            val async1 = async {
//                delay(1000)
//                Log.e("debug1", "1000: ")
//                3
//            }
//            val async2 = async {
//                delay(3000)
//                Log.e("debug1", "3000: ")
//                6
//            }
//
//
//            val i = async1.await() + async2.await()
//            Log.e("debug1", "i: $i")
//        }
//        launch {
//
//            val async1 = launch {
//                delay(1000)
//                Log.e("debug2", "1000: ")
//                3
//            }
//            val async2 = launch {
//                delay(3000)
//                Log.e("debug2", "3000: ")
//
//                6
//            }
//
//
//            Log.e("debug2", "i: $")
//        }


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
            val async1 = async {
                get1()
                99
            }
            val async2 = async {
                get2()
            }
            val async3 = async {
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


    fun getUNDISPATCHED() {
        Log.e("debug", "start " + Thread.currentThread().name)
        //DEFAULT顺序执行
        //  UNDISPATCHED 协程体里面有suspend就异步执行，下面的继续执行
        viewModelScope.launch(context = Dispatchers.Main, start = CoroutineStart.UNDISPATCHED) {

            Log.e("debug", "UNDISPATCHED start " + Thread.currentThread().name)
            delay(5000)
            Log.e("debug", "UNDISPATCHED end " + Thread.currentThread().name)
        }
        Log.e("debug", "end " + Thread.currentThread().name)
    }

    fun delayCancel() {
        launch(cancel = object : Cancel {
            override fun invoke(e: Exception) {
                Log.e("debug", "Cancel e: " + e.localizedMessage)
            }
        }) {
            repeat(10000) {
                Log.e("debug", "repeat: $it")
            }
        }
    }

    suspend fun dea() {
        val job = GlobalScope.launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                println("job: I'm running finally")
            }
        }


        delay(1300L) // 延迟一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并且等待它结束
        println("main: Now I can quit.")
    }
}