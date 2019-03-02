package com.dtb.home.controller;

import com.dtb.utils.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 14:54 2019/2/24.
 * @ModifyBy：
 */
@Controller("indexController")
@RequestMapping("home/index")
public class IndexController {

    /**
     * @auther: lmx
     * @descript: 渲染主页视图
     * @date: 2019/2/28 17:46
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("index")
    public String index(){
        return "home/index";
    }

    /**
     * @auther: lmx
     * @descript: 渲染注册页视图
     * @date: 2019/2/28 17:46
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("register")
    public String register(){
        return "home/register";
    }

    /**
     * @auther: lmx
     * @descript: 渲染登录页视图渲染
     * @date: 2019/2/28 17:46
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping("login")
    public String login(){
        return "home/login";
    }

    /**
     * @auther: lmx
     * @descript: 返回二维码图片，并且在session存入当前验证码字符串
     * @date: 2019/2/28 17:45
     * @param: [response, request]
     * @return: void
     */
    @GetMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpServletRequest request) throws Exception{
        HttpSession session=request.getSession();
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.createImage();
        //将验证码存入Session
        session.setAttribute("verifyCode",objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    /**
     * @auther: lmx
     * @date: 2019/3/3 1:14
     * @descript: 关于我们页面渲染
     * @param:
     * @return: java.lang.String
     */
    @RequestMapping("about")
    public String about(){
        return "home/about";
    }
}
