package xyz.yisheng.blog.service;


import priv.framework.jdbc.ActiveRecord;
import priv.framework.mvc.annotation.Service;
import xyz.yisheng.blog.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/2.
 * UserDAO
 */
@Service
public class UserDAO  {

    ActiveRecord activeRecord =new ActiveRecord();

    public void save(User user) {
        activeRecord.save(user);
    }
    public User findByID(int key) {
        return activeRecord.get(new User(),key);
    }


}
