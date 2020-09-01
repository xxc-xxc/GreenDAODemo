package com.uos.greendaodemo.db;

import com.uos.greendaodemo.bean.User;
import com.uos.greendaodemo.greendao.gen.UserDao;

import java.util.List;

/**
 * Create By xxc
 * Date: 2020/9/1 14:05
 * Desc:
 */
public class UserDaoHelper {

    private static UserDaoHelper sUserDaoHelper;
    private UserDao mUserDao;

    private UserDaoHelper() {
        mUserDao = GreenDAOManager.getDaoSession().getUserDao();
    }

    public static UserDaoHelper getInstance() {
        if (sUserDaoHelper == null) {
            synchronized (UserDaoHelper.class) {
                if (sUserDaoHelper == null) {
                    sUserDaoHelper = new UserDaoHelper();
                }
            }
        }

        return sUserDaoHelper;
    }

    /**
     * 增
     */
    public void insertUser(User user) {
        mUserDao.insert(user);
    }

    public void insertMulUser(List<User> userList) {
        mUserDao.insertInTx(userList);
//        mUserDao.insertOrReplaceInTx(userList);
    }

    /**
     * 删除
     * @param user
     */
    public void deleteUser(User user) {
        mUserDao.delete(user);
    }

    public void deleteUser(long id) {
        mUserDao.deleteByKey(id);
    }

    /**
     * 更新
     * @param user
     */
    public void updateUser(User user) {
        mUserDao.update(user);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public User getUser(long id) {
        return mUserDao.load(id);
    }

    public List<User> getUserList() {
        return mUserDao.loadAll();
    }

}
