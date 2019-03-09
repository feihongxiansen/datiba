package com.dtb.home.controller;

import com.dtb.entity.QuestionsAssociation;
import com.dtb.home.service.QAService;
import com.dtb.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private QAService qaService;

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
     * @auther lmx
     * @date 2019/3/10 0:54
     * @descript 渲染登录页视图渲染
     * @param pagePath 登录后跳转的页面
     * @param model 视图
     * @return java.lang.String
     */
    @RequestMapping("login")
    public String login(@RequestParam(value = "pagePath",required = false,defaultValue = "/home/index/index") String pagePath,
                        Model model){
        model.addAttribute("pagePath",pagePath);
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

    /**
     * @auther: lmx
     * @date: 2019/3/3 11:24
     * @descript: 有问有答页面渲染
     * @param:
     * @return: java.lang.String
     */
    @RequestMapping("question")
    public String question(){
        return "home/question";
    }

    /**
     * @auther: lmx
     * @date: 2019/3/9 1:28
     * @descript: 问题详情和用户解答列表页面
     * @param:
     * @return: java.lang.String
     */
    @RequestMapping("answer/{questionId}")
    public String answer(@PathVariable("questionId") Integer questionId,Model model){
        QuestionsAssociation questionAndAnswers = qaService.findAnswerList(questionId);
        model.addAttribute("questionInfo",questionAndAnswers);
        return  "home/answer";
    }

}
