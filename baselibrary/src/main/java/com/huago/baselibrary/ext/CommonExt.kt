package com.yinxin1os.im.utils.ext

import android.text.Editable
import android.view.View
import android.widget.EditText



interface DefaultTextWatchListener {
    fun onTextChangedCallBack(text: String)
}

fun EditText.addKtTextWatch(listener: DefaultTextWatchListener) {
    addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            super.onTextChanged(s, start, before, count)
            listener.onTextChangedCallBack(s.toString())
        }
    })

}

fun Editable?.getSafeContent(): String {
    return if (isNullOrBlank()) "" else toString().trim()
}

fun String?.getSafeContent(): String {
    return this?.trim() ?: ""
}

fun EditText?.getTrimString(): String {
    return if (this == null) "" else text?.getSafeContent() ?: ""
}

/*
    扩展点击事件
 */
fun View.onClick(listener: View.OnClickListener): View {
    //    setOnClickListener(listener)
    setOnClickListener {
        if (!doubleClick()) {
            listener.onClick(this)
        }
    }
    return this
}

private var time: Long = 0
private var howlong: Long = 0

fun doubleClick(): Boolean {
    val currentTimeMillis = System.currentTimeMillis()
    howlong = currentTimeMillis - time
    time = currentTimeMillis
    return howlong <= 1000
}

/*
    扩展点击事件，参数为方法
 */
fun View.onClick(method: () -> Unit): View {
    setOnClickListener {
        if (!doubleClick()) {
            method()
        }
    }
    return this
}