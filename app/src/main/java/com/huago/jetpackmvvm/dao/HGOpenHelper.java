package com.huago.jetpackmvvm.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.huago.jetpackmvvm.util.MigrationHelper;


/**
 * @author Administrator
 * @updateDes 2020/6/16
 */
public class HGOpenHelper extends DaoMaster.OpenHelper {

    public HGOpenHelper(Context context, String name) {
        super(context, name);
    }

    public HGOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        // 迁移数据库(如果修改了多个实体类，则需要把对应的Dao都传进来)
        MigrationHelper.migrate(db, UserInfoDao.class);
    }
}