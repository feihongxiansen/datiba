package com.dtb.config;

import com.dtb.config.intercepors.HomeLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 15:14 2019/2/24.
 * @ModifyBy：
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private HomeLoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/static/home/**"
                ,"/static/upload/images/**"    //上传图片路径
                ,"/home/user/checkLogin"    //登录验证
                ,"/home/user/getUserList"    //获取用户列表
                ,"/home/user/logOut"        //退出登录
                ,"/home/user/checkEmailExist"//检测邮箱地址是否可用
                ,"/home/user/register"      //用户注册
                ,"/home/user/activation/**"      //激活账号
                ,"/home/user/resetEmailCode/**"      //重新发送验证邮件
                ,"/home/index/**"
                ,"/home/question/getQuestionList" //获取问题列表
                ,"/home/question/getAnswerList/**" //获取答案列表
                ,"/home/question/uploadImages" //上传多图
                ,"/home/grade/getGradeList" //获取年级列表
                ,"/home/subject/getSubjectList" //获取学科列表
//                ,"/home/index/register"     //注册页面
//                ,"/home/index/login"        //登录页面
//                ,"/home/index/about"     //联系我们页面
//                ,"/home/index/getVerifyCode"//获取验证码图片
//                ,"/home/index/index"
        );       //主页
    }
}
