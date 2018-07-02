package xyz.yisheng.blog.controller;


import priv.framework.mvc.annotation.Action;
import priv.framework.mvc.annotation.Controller;
import priv.framework.mvc.annotation.Inject;
import priv.framework.mvc.bean.Data;
import priv.framework.mvc.bean.Param;
import priv.framework.mvc.bean.View;
import xyz.yisheng.blog.model.User;
import xyz.yisheng.blog.service.UserDAO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/3.
 * 控制层
 */
@Controller
public class UserController {
    @Inject
    private UserDAO userDAO;

    private static void addCookie(String name, String value, int maxAge, HttpServletResponse response) throws Exception {
        //添加cookie操作
        Cookie cookie = new Cookie(name, URLEncoder.encode(value.trim(), "UTF-8"));
        cookie.setMaxAge(maxAge);// 设置为10天
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Action("get:/index")
    public View index(Param param) {
        return new View("index.jsp");
    }

    @Action("post:/validate")
    public Data validate(Param param) throws Exception {

        Map<String, String> map = new HashMap();
        User user1 =userDAO.findByID(param.getInt("uid"));
        //登录成功返回user，失败返回null
        if (!user1.getPassword().equals(param.getString("password"))) {
            map.put("result", "密码不正确！");
            return new Data(map);
        } else {
            map.put("result", "SUCCESS");
            //取出放到param中的HttpServletResponse
            addCookie("uid", String.valueOf(user1.getUid()), 1 * 12 * 60 * 60, param.getResponse());
            addCookie("username", user1.getUsername(), 1 * 12 * 60 * 60, param.getResponse());
            return new Data(map);
        }
    }

    @Action("post:/register")
    public Data register(Param param) throws Exception {
        Map<String, String> result = new HashMap();
        Map<String, Object> result2=new HashMap<String, Object>();
        User user=new User();
        //  user.setPassword(param.getString("password"));
        user.setUid(param.getInt("uid"));//这里待简化
        user.setUsername(param.getString("username"));
        user.setPassword(param.getString("password"));

        userDAO.save(user);
        addCookie("uid", String.valueOf(user.getUid()), 1 * 24 * 60 * 60, param.getResponse());
        addCookie("username", user.getUsername(), 1 * 24 * 60 * 60, param.getResponse());
        result.put("result", "SUCCESS");
        return new Data(result);
    }

    @Action("post:/changePasswd")
    public View changePasswd(Param param) {
        String newPassword=param.getString("newPassword");
        int uid=param.getInt("uid");
        User user = userDAO.findByID(uid);
        user.setPassword(newPassword);
        //   userDAO.save(user);
        return new View("homePage.jsp");
    }

    @Action("get:/logout")
    public View logout(Param param) throws Exception {
        int uid=param.getInt("uid");
        User user =  userDAO.findByID(uid);
        addCookie("uid", String.valueOf(user.getUid()), 0, param.getResponse());
        addCookie("username", user.getUsername(), 0, param.getResponse());

        return new View("/index");
    }
}