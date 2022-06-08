package com.nateshao.login.test.controller;

import com.nateshao.login.test.annotation.Auth;
import com.nateshao.login.test.models.User;
import com.nateshao.login.test.repo.UserRepo;
import com.nateshao.login.test.service.UserService;
import com.nateshao.login.test.utils.AjaxResult;
import com.sun.xml.bind.v2.model.core.ID;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    /**
     * 通过userName和passWord
     *
     * @param userName
     * @param passWord
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/api/login")
    public AjaxResult<User> login(@RequestParam String userName, @RequestParam String passWord) {
        User user = userService.loginService(userName, passWord);
        if (user != null) {
//            if (user.getuserName() == "MrKrabs")
            return AjaxResult.success(user);
        } else {
            return AjaxResult.error("401", "账号或密码错误！");
        }
    }

    @PostMapping("/register")
    public AjaxResult<User> regist(@RequestParam String userName, @RequestParam String passWord) {
        User user = userService.registService(userName, passWord);
        if (user != null) {
            return AjaxResult.success(user);
        } else {
            return AjaxResult.error("402", "用户名已存在！");
        }
    }

    /**
     * 退出
     *
     * @param
     * @return
     */
    @Auth
    @GetMapping("/api/logout")
    @ApiOperation("退出")
    public AjaxResult logout(String userName) {
        userService.logout(userName);
        return AjaxResult.success("退出成功");
    }

    @GetMapping("/api/getUsers")
    @ApiOperation("获取所有用户")
    public AjaxResult<List<User>> getUser() {
        List<User> list = userRepo.findAll();
        return AjaxResult.success(list);
    }

    /**
     * 用于删除当前选定的用户
     *
     * @param userName
     * @return
     */
    @DeleteMapping("/api/delUserByUsername")
    public AjaxResult delUserByUsername(@RequestParam String userName) {
        userService.delUser(userName);
        return AjaxResult.success();
    }


    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome!";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/search/{id}")
    public Optional<User> searchUser(@PathVariable long id) {
        return userRepo.findById(id);
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "success...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            User updatedUser = optional.get();
            updatedUser.setuserName(user.getuserName());
            updatedUser.setpassWord(user.getpassWord());
            userRepo.save(updatedUser);
            return "Updated...";
        } else {
            return "No such user found!";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            User deleteUser = optional.get();
            userRepo.delete(deleteUser);
            return "Delete user with the id: " + id;
        } else {
            return "No such user found!";
        }
    }

}
