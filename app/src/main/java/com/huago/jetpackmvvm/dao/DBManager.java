package com.huago.jetpackmvvm.dao;

import android.content.Context;


public class DBManager {

    private final static String DB_NAME = "hg_db";
    private static DBManager instance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static Context mContext;
    private static boolean isInitialized;

    /**
     * [获取DBManager实例，单例模式实现]
     *
     * @return DBManager
     */
    public static DBManager getInstance() {
        return instance;
    }

    /**
     * [初始化DBManager实例，单例模式实现]
     *
     * @param context
     * @return DBManager
     */
    public static DBManager init(Context context) {
        if (isInitialized) {
            return instance;
        }
        mContext = context;
        instance = new DBManager(context);
        isInitialized = true;
        return instance;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * 构造方法
     *
     * @param context
     */
    private DBManager(Context context) {
        HGOpenHelper helper = new
                HGOpenHelper(context, DB_NAME, null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        //数据库存贮路径修改,直接删除旧的数据库
        mContext.deleteDatabase(mContext.getPackageName());
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public void setDaoMaster(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }


    public void uninit() {
        if (daoSession != null && daoSession.getDatabase() != null) {
            daoSession.getDatabase().close();
        }
        daoSession = null;
        daoMaster = null;
        isInitialized = false;
    }

    public UserInfoDao getUserInfoDao() {
        if (daoSession == null) {
            return null;
        }
        return daoSession.getUserInfoDao();
    }

}
