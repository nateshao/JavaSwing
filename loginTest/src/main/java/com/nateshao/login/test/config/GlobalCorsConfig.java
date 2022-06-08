//package com.nateshao.login.test.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @date Created by 邵桐杰 on 2022/6/7 21:40
// * @微信公众号 千羽的编程时光
// * @个人网站 www.nateshao.cn
// * @博客 https://nateshao.gitlab.io
// * @GitHub https://github.com/nateshao
// * @Gitee https://gitee.com/nateshao
// * Description:
// */
//@Configuration
//public class GlobalCorsConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
//                        .allowedOrigins("*")    //开放哪些ip、端口、域名的访问权限
//                        .allowCredentials(true)  //是否允许发送Cookie信息
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")     //开放哪些Http方法，允许跨域访问
//                        .allowedHeaders("*")     //允许HTTP请求中的携带哪些Header信息
//                        .exposedHeaders("*");   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//            }
//        };
//    }
//}
//
