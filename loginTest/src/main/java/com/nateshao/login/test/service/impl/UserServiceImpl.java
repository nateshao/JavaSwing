package com.nateshao.login.test.service.impl;

import com.nateshao.login.test.models.User;
import com.nateshao.login.test.repo.UserRepo;
import com.nateshao.login.test.service.UserService;
import com.nateshao.login.test.utils.StringUtils;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @date Created by 邵桐杰 on 2022/6/7 20:49
 * @微信公众号 千羽的编程时光
 * @个人网站 www.nateshao.cn
 * @博客 https://nateshao.gitlab.io
 * @GitHub https://github.com/nateshao
 * @Gitee https://gitee.com/nateshao
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userDao;

    @Override
    public User loginService(String userName, String passWord) {
//        if (userName == null || userName == "") return null;
        // 如果账号密码都对则返回登录的用户对象，若有一个错误则返回null
        User user = userDao.findByUserNameAndPassWord(userName, passWord);
        // 重要信息置空
        if (user != null) {
            user.setpassWord("");
        }
        return user;
    }

    @Override
    public User registService(String userName, String passWord) {
        //当新用户的用户名已存在时
        if (userDao.findByUserName(userName) != null) {
            // 无法注册
            return null;
        } else {
            //返回创建好的用户对象(带uid)
            User user = new User();
            user.setuserName(userName);
            user.setpassWord(passWord);
            User newUser = userDao.save(user);
            return newUser;
        }
    }

    @Override
    public void delUser(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            User newUser = userDao.findByUserName(userName);
            userDao.delete(newUser);
        }
    }

    @Override
    public void logout(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            User newUser = userDao.findByUserName(userName);
            userDao.delete(newUser);
        }
    }


}
