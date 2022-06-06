package com.nateshao.login.service;


import com.nateshao.login.domain.Student;

import java.util.List;

/**
 * @date Created by 邵桐杰 on 2022/06/04 10:06
 * @微信公众号 程序员千羽
 * @个人网站 www.nateshao.cn
 * @博客 https://nateshao.gitee.io
 * @GitHub https://github.com/nateshao
 * @Gitee https://gitee.com/nateshao
 * Description:
 */
public interface StudentService {

    public List<Student> queryStudent();

    public Student queryStudentsById(Integer id);

    int addStudent(String studName, int stuNo, String sex, String nation, String political, String school, String major, String birthday, String entranceTime);

    void editstudentByStuNo(Student student);

    int delStudentByStuNo(int stuNo);

}
