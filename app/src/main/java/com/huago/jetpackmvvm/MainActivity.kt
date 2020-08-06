package com.huago.jetpackmvvm

import BaseDataBindActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base_mvvm.adapter.UserInfoAdapter
import com.fmt.github.base.viewmodel.BaseViewModel
import com.huago.baselibrary.activity.BaseDataBindVMActivity
import com.huago.jetpackmvvm.dao.UserInfo
import com.huago.jetpackmvvm.databinding.BaseActivityMainBinding
import com.huago.jetpackmvvm.viewmodel.MainViewModel
import com.yinxin1os.im.utils.ext.onClick
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.math.log

class MainActivity(override val layoutRes: Int = R.layout.base_activity_main) :
    BaseDataBindVMActivity<BaseActivityMainBinding>() {

    private val mViewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }


    override fun initView() {
        mDataBind.btnSave.onClick {

            val userInfo = UserInfo()
            userInfo.userName = mDataBind.editUserName.text.trim().toString()
            userInfo.pwd = mDataBind.editPwd.text.trim().toString()
            userInfo.sex =
                if (mDataBind.editSex.text.isNullOrEmpty()) -11 else mDataBind.editSex.text.trim()
                    .toString().toInt()
            mViewModel.saveUserInfo(userInfo)

        }
        mDataBind.btnLoad.onClick {
//            lifecycleScope.launch {
            mViewModel.loadUserInfoList()
//            }
            Log.e("debug", "onClick loadUserInfoList finish: ")
        }



        mDataBind.btnJump.onClick {
            startActivity(Intent(this, SecActivity::class.java))
        }


        mViewModel.userInfos.observe(this,
            Observer<MutableList<UserInfo>> { t ->
                Toast.makeText(this@MainActivity, "update ", Toast.LENGTH_SHORT).show()
                Log.e("debug", "observe: " + Thread.currentThread().name)

                Log.e(
                    "debug",
                    "update: " + t?.forEach {
                        Log.e(
                            "debug",
                            "id: ${it.userId}  userName: ${it.userName}  pwd:${it.pwd} sex :${it.sex}"
                        )
                    })
                mAdapter?.setNewData(t)
            })

        initRVList()


//        mViewModel.merge()
//mViewModel.delayCancel()

        mViewModel.getUNDISPATCHED()

    }

    suspend fun test() {
        Executors.newSingleThreadExecutor().asCoroutineDispatcher()
            .use { dispatcher ->
                GlobalScope.launch(dispatcher) {
                    val job = launch {
                        delay(1000)
                        "Hello"
                    }
                    val result = job.join()
                }.join()
            }
    }

    suspend fun test2() {
        GlobalScope.launch(newSingleThreadContext("Dispather")) {
            val job = launch {
                delay(1000)
                "Hello"
            }
            val result = job.join()
        }.join()
    }

    val mAdapter: UserInfoAdapter by lazy { UserInfoAdapter() }


    private fun initRVList() {



        with(mDataBind.rvlist) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun getViewModel(): BaseViewModel {
        return mViewModel
    }


}