package com.example.base_mvvm.Initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.huago.jetpackmvvm.dao.DBManager
import java.util.*


class DaoInitializer : Initializer<DBManager> {
    override fun create(context: Context): DBManager {
        Log.e("debug", "dbmanager 初始化 ")
        return DBManager.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return Collections.emptyList()
    }
}