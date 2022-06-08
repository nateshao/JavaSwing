package com.nateshao.login.test.repo;

import com.nateshao.login.test.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public User findByUserName(String userName); //通过用户名userName查找用户，注意要按照JPA的格式使用驼峰命名法

    public User findByUserNameAndPassWord(String userName, String passWord);//通过用户名userName和密码查找用户
}
