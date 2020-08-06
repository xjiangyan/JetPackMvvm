package com.fmt.github.base.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.huago.baselibrary.utils.LogUtil
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

//类型别名
typealias LaunchBlock = suspend CoroutineScope.() -> Any

typealias EmitBlock<T> = suspend LiveDataScope<T>.() -> T

typealias Cancel = (e: Exception) -> Unit

open class BaseViewModel : ViewModel() {

    val mStateLiveData = MutableLiveData<StateActionEvent>()//通用事件模型驱动(如：显示对话框、取消对话框、错误提示)

    fun launch(
        cancel: Cancel? = null,
        context: CoroutineContext = Dispatchers.IO,
        block: LaunchBlock
    ) {
//        val launch =
//            viewModelScope.launch() {
//                //ViewModel自带的viewModelScope.launch,会在页面销毁的时候自动取消请求,有效封装内存泄露
//                Log.e("debug", "running: " + Thread.currentThread().name)
//                try {
//                    mStateLiveData.value = LoadState
//                    withContext(context) {
//                        block()
//                    }
//                    mStateLiveData.value = SuccessState
//                } catch (e: Exception) {
//                    Log.e("debug2", "viewModelScope.Exception: $e")
//                    when (e) {
//                        is CancellationException -> cancel?.invoke(e)
//                        else -> mStateLiveData.value = ErrorState(e.message)
//                    }
//                }
//
//            }

            viewModelScope.launch(Dispatchers.Main + CoroutineExceptionHandler { _, throwable ->
                when (throwable) {
                    is CancellationException -> cancel?.invoke(throwable)
                    else -> mStateLiveData.value = ErrorState(throwable.localizedMessage)
                }
            }, start = CoroutineStart.DEFAULT) {

                mStateLiveData.value = LoadState
                LogUtil.e("LoadState: ")
                withContext(context) {
                    block()
                }
                mStateLiveData.value = SuccessState
                LogUtil.e("SuccessState: ")
            }


    }

    fun async(
        cancel: Cancel? = null,
        context: CoroutineContext = Dispatchers.IO,
        block: LaunchBlock
    ) = viewModelScope.async {
        Log.e("debug", "running: " + Thread.currentThread().name)

        withContext(context) {
            val block1 = block()
            block1
        }
    }


    fun <T> emit(cancel: Cancel? = null, block: EmitBlock<T>): LiveData<T> = liveData {
        try {
            mStateLiveData.value = LoadState
            emit(block())
            mStateLiveData.value = SuccessState
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> cancel?.invoke(e)
                else -> mStateLiveData.value = ErrorState(e.message)
            }
        }
    }
}