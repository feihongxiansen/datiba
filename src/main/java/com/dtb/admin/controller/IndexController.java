package com.dtb.admin.controller;

import com.dtb.admin.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/30-10:46
 */
@RequestMapping("/admin/index")
@Controller("adminIndexController")
public class IndexController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    /**
     * 登录页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 10:48
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return "redirect:/admin/index/index";
        }
        return "admin/login";
    }

    /**
     * 管理系统主页渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 11:14
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    /**
     * 欢迎页渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 11:18
     */
    @RequestMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("count2All", dataAnalysisService.overallStatistics("ALL"));
        model.addAttribute("count2Today", dataAnalysisService.overallStatistics("TODAY"));
        model.addAttribute("count2Yesterday", dataAnalysisService.overallStatistics("YESTERDAY"));
        model.addAttribute("count2ThisWeek", dataAnalysisService.overallStatistics("THISWEEK"));
        model.addAttribute("count2ThisMonth", dataAnalysisService.overallStatistics("THISMONTH"));
        return "admin/welcome";
    }

    /**
     * 404页面
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 21:19
     */
    @RequestMapping("/notFound")
    public String notFound() {
        return "admin/404";
    }

    /**
     * 500页面
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 21:19
     */
    @RequestMapping("/error")
    public String error() {
        return "admin/500";
    }
}
