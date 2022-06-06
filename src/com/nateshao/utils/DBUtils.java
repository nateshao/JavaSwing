package com.nateshao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @date Created by 邵桐杰 on 2022/6/6 16:44
 * @微信公众号 千羽的编程时光
 * @个人网站 www.nateshao.cn
 * @博客 https://nateshao.gitlab.io
 * @GitHub https://github.com/nateshao
 * @Gitee https://gitee.com/nateshao
 * Description: 数据库工具类
 */
public class DBUtils {
    //数据库地址
    private String dbUrl = "jdbc:mysql://localhost:3306/test?useSSL=true";
    //用户名
    private String dbUserName = "root";
    //密码
    private String dbPassword = "123456";
    //驱动名称
    private String jdbcName = "com.mysql.jdbc.Driver";
    // 获取数据库连接
    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }
    // 关闭数据库连接
    public void closeCon(Statement smt, Connection con) throws Exception {
        if (smt != null) {
            smt.close();
        }
        if (con != null) {
            con.close();
        }
    }

    // 关闭数据库连接
    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
