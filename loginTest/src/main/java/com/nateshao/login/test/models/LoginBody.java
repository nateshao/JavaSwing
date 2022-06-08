package com.nateshao.login.test.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @date Created by 邵桐杰 on 2022/6/7 20:59
 * @微信公众号 千羽的编程时光
 * @个人网站 www.nateshao.cn
 * @博客 https://nateshao.gitlab.io
 * @GitHub https://github.com/nateshao
 * @Gitee https://gitee.com/nateshao
 * Description:
 */
@Data
@ApiModel("登入请求体")
public class LoginBody implements Serializable {

    /**
     * 用户名称
     */
    @NotBlank(message = "userName")
    @Size(min = 1, max = 20, message = "用户名称userName长度必须在 {min} - {max} 之间")
    @ApiModelProperty("用户名称")
    private String userName;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码passWord不能为空")
    @ApiModelProperty("用户密码")
    private String passWord;

    @ApiModelProperty("app本机唯一标识")
    private String clientId;

    @ApiModelProperty("手机型号")
    private String phoneModel;
}
