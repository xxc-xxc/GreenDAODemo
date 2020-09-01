package com.uos.greendaodemo.db;

import com.uos.greendaodemo.MyApplication;
import com.uos.greendaodemo.greendao.gen.DaoMaster;
import com.uos.greendaodemo.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Create By xxc
 * Date: 2020/9/1 10:40
 * Desc:
 */
public class GreenDAOManager {

    private static final String DB_NAME = "com.uos.demo.db";
    private static GreenDAOManager mInstance;
    private DaoMaster.DevOpenHelper mHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private GreenDAOManager() {
        if (mInstance == null) {
            mHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), DB_NAME, null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    /**
     * 双重校验锁实现懒汉式单例
     * @return
     */
    public static GreenDAOManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDAOManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDAOManager();
                }
            }
        }

        return mInstance;
    }

    public static DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * 设置debug模式开启or关闭
     * @param flag
     */
    public void setDebug(boolean flag) {
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    /**
     * 关闭数据库
     */
    public void closeDatabase() {
//        closeHelper();
        closeDaoSession();
    }

    private void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    private void closeHelper() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

}
