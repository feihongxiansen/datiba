package com.dtb.config;

import com.dtb.config.intercepors.AdminLoginInterceptor;
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
    private HomeLoginInterceptor homeLoginInterceptor;
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

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
        //前台拦截器
        registry.addInterceptor(homeLoginInterceptor).addPathPatterns("/home/**").excludePathPatterns("/static/**"
                , "/static/upload/images/**"    //上传图片路径
                , "/home/user/checkLogin"    //登录验证
                , "/home/user/getUserList"    //获取用户列表
                , "/home/user/getUserListToLimit/**"    //根据用户类型获取用户列表
                , "/home/user/logOut"        //退出登录
                , "/home/user/checkEmailExist"//检测邮箱地址是否可用
                , "/home/user/register"      //用户注册
                , "/home/user/activation/**"      //激活账号
                , "/home/user/resetEmailCode/**"      //重新发送验证邮件
                , "/home/index/**"
                , "/home/question/getQuestionList" //获取问题列表
                , "/home/question/getAnswerList/**" //获取答案列表
                , "/home/question/uploadImages" //上传多图
                , "/home/question/addQuestion" //上传多图
                , "/home/grade/getGradeList" //获取年级列表
                , "/home/subject/getSubjectList" //获取学科列表
                , "/home/document/list"  //文档列表
                , "/home/document/searchListToLimit"  //分页搜索文件
                , "/home/document/detial/**"  //文件详情
                , "/home/document/downloadCheck/**"  //文件下载检查
                , "/home/gift/queryGiftList/**"  //积分兑换区列表查询
        );

        //后台管理系统拦截器
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns(
                "/admin/index/login"    //后台管理系统登录页面不拦截
                , "/admin/admin/checkLogin" //登录检测
        );
    }
}
