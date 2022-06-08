package com.nateshao.login.test.service;

import com.nateshao.login.test.models.User;

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
public interface UserService {
    /**
     * 登录业务逻辑
     *
     * @param userName 账户名
     * @param passWord 密码
     * @return
     */
    User loginService(String userName, String passWord);


    User registService(String userName, String passWord);

    void delUser(String userName);

    void logout(String userName);

}
