package com.uos.greendaodemo.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.uos.greendaodemo.R;
import com.uos.greendaodemo.bean.User;
import com.uos.greendaodemo.db.GreenDAOManager;
import com.uos.greendaodemo.db.UserDaoHelper;
import com.uos.greendaodemo.greendao.gen.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        insertData();
//        queryData();
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("xyx");
        user1.setAge(22);
        user1.setGender("女男女");

        User user2 = new User();
        user2.setName("cxx");
        user2.setAge(24);
        user2.setGender("女");
        users.add(user1);
        users.add(user2);
//        UserDaoHelper.getInstance().insertMulUser(users);

        UserDaoHelper.getInstance().updateUser(user1);

        List<User> userList = UserDaoHelper.getInstance().getUserList();
        for (User user : userList) {
            Log.d(TAG, "onCreate: " + user.toString());
        }
    }

    /**
     * 增
     */
    private void insertData() {
        UserDao userDao = GreenDAOManager.getDaoSession().getUserDao();
        User user = new User();
        user.setName("xxc");
        user.setAge(18);
        user.setGender("man");
        userDao.insert(user);
    }

    /**
     * 查询数据
     */
    private void queryData() {
        UserDao userDao = GreenDAOManager.getDaoSession().getUserDao();
        List<User> users = userDao.loadAll();
        Log.d(TAG, "queryData: " + users.get(0).getName());
    }
}