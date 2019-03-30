package com.dtb.admin.controller;

import com.dtb.admin.service.AdminService;
import com.dtb.entity.Admin;
import com.dtb.utils.MD5Util;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/30-13:15
 */
@RequestMapping("admin/admin")
@Controller("adminAdminController")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private Environment environment;
    @Value("${com.dtb.security.md5.key}")
    private String md5Key;

    /**
     * 登录检测
     *
     * @param admin      参数
     * @param session    会话session
     * @param verifyCode 验证码
     * @return com.dtb.utils.resulthandler.ResponseBean<java.lang.String>
     * @author lmx
     * @date 2019/3/30 14:01
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public ResponseBean checkLogin(Admin admin,
                                   HttpSession session,
                                   @RequestParam String verifyCode) {
        //首先验证验证码是否存在
        String trueVerifyCode = (String) session.getAttribute("verifyCode");
        if (trueVerifyCode == null) {
            return new ResponseBean(false, CommonErrorEnum.REFRESH_VERIFYCODE);
        }

        //判断验证码是否输入正确（验证码忽略大小写）
        if (!trueVerifyCode.equalsIgnoreCase(verifyCode)) {
            return new ResponseBean(false, CommonErrorEnum.ERROR_VERIFYCODE);
        }

        //根据手机号查找用户
        Admin queryParam = new Admin();
        queryParam.setEmail(admin.getEmail());
        Admin dbAdmin = adminService.findOne(queryParam);
        //判断是否存在当前用户
        if (dbAdmin == null) {
            return new ResponseBean(false, CommonErrorEnum.NO_USER_EXIST);
        }

        //判断密码是否正确
        if (!MD5Util.md5Verify(admin.getPassword(), this.md5Key, dbAdmin.getPassword())) {
            return new ResponseBean(false, CommonErrorEnum.INVALID_PASSWORD);
        }

        //判断用户当前是否被禁止登录
        if (!dbAdmin.getLoginState()) {
            return new ResponseBean(false, CommonErrorEnum.BAN_LOGIN);
        }

        //通过所有验证
        session.setAttribute("admin", dbAdmin);
        return new ResponseBean<>(true, "登录成功！", CommonErrorEnum.LOGIN_SUCCESS);
    }

    /**
     * 登出
     *
     * @param session 会话session
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/30 15:07
     */
    @RequestMapping("logOut")
    @ResponseBody
    public ResponseBean logOut(HttpSession session) {
        session.removeAttribute("admin");
        return new ResponseBean(true, CommonErrorEnum.LOGOUT_SUCCESS);
    }

    /**
     * 管理员列表页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 16:10
     */
    @RequestMapping("adminListPage")
    public String adminListPage() {
        return "admin/admin/list";
    }

    /**
     * 分页查询管理员列表
     *
     * @param pageNum  页码
     * @param pageSize 每页显示条数
     * @param admin    查询参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/30 16:13
     */
    @RequestMapping("getAdminListLimit/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getAdminListLimit(@PathVariable Integer pageNum,
                                          @PathVariable Integer pageSize,
                                          Admin admin) {
        PageHelper.startPage(pageNum, pageSize);
        Page<List<Admin>> pageList = adminService.findPageAdminList(admin);

        //分页信息
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("total", pageList.getTotal());
        pageInfo.put("pages", pageList.getPages());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("adminList", pageList);
        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据启用状态精确查询，对昵称、姓名、邮箱、电话进行模糊查询
     *
     * @param pageNum    当前页码
     * @param pageSize   每页显示条数
     * @param loginState 启用状态
     * @param param      模糊查询参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/30 18:15
     */
    @RequestMapping("getAdminListVagueLimit/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getAdminListVagueLimit(@PathVariable Integer pageNum,
                                               @PathVariable Integer pageSize,
                                               @RequestParam("loginState") Boolean loginState,
                                               @RequestParam("param") String param) {
        PageHelper.startPage(pageNum, pageSize);
        Page<List<Admin>> pageList = adminService.findPageAdminListByVague(loginState, param);

        //分页信息
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("total", pageList.getTotal());
        pageInfo.put("pages", pageList.getPages());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("adminList", pageList);
        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据ID修改信息
     *
     * @param admin 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/30 18:48
     */
    @RequestMapping("updateAdminById")
    @ResponseBody
    public ResponseBean updateAdminById(Admin admin) {
        if (null == admin) {
            return new ResponseBean<String>(true, "修改参数为空", CommonErrorEnum.SUCCESS_OPTION);
        }
        adminService.updateByIdSelective(admin);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 管理员编辑页面渲染
     *
     * @param adminId 管理员id
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 20:58
     */
    @RequestMapping("editPage/{adminId}")
    public String editPage(@PathVariable Integer adminId, Model model) {
        Admin admin = new Admin();
        admin.setId(adminId);
        model.addAttribute("admin", adminService.findOne(admin));
        return "admin/admin/edit";
    }

    /**
     * 修改管理员信息
     *
     * @param admin 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/30 22:48
     */
    @RequestMapping("edit")
    @ResponseBody
    public ResponseBean edit(Admin admin) {
        //检测邮箱是否可用
        Admin dbAdmin = adminService.findById(admin.getId());
        if (dbAdmin != null && !dbAdmin.getEmail().equals(admin.getEmail())) {
            //如果邮箱被修改过，检测是否可用
            ResponseBean res = this.emailExist(admin.getEmail());
            if (res.isSuccess()) {
                return new ResponseBean(false, CommonErrorEnum.REPEAT_EMAIL);
            }
        }
        String password = admin.getPassword();
        //如果更新了密码，对密码加密保存
        if (password != null && password.trim() != "") {
            admin.setPassword(MD5Util.md5(password, this.md5Key));
        } else {
            admin.setPassword(null);
        }
        int result = adminService.updateByIdSelective(admin);
        if (result > 0) {
            return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
        }
        return new ResponseBean<String>(false, "信息未被修改", CommonErrorEnum.FAILED_QUESTION);
    }

    /**
     * 检测邮箱是否存在
     *
     * @param email 邮箱地址
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/30 23:31
     */
    @RequestMapping("emailExist")
    @ResponseBody
    public ResponseBean emailExist(@RequestParam(value = "email", required = true) String email) {
        Admin queryParam = new Admin();
        queryParam.setEmail(email);
        PageHelper.startPage(1, 10);
        Page<List<Admin>> pageList = adminService.findPageAdminList(queryParam);
        if (pageList == null || pageList.getTotal() <= 0) {
            return new ResponseBean(false, CommonErrorEnum.NOT_EXIST_EMAIL);
        }
        return new ResponseBean<Page<List<Admin>>>(true, pageList, CommonErrorEnum.REPEAT_EMAIL);
    }

    /**
     * 添加管理员页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/30 23:46
     */
    @RequestMapping("addPage")
    public String addPage() {
        return "/admin/admin/add";
    }

    /**
     * 添加管理员
     *
     * @param admin 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 0:08
     */
    @RequestMapping("add")
    @ResponseBody
    public ResponseBean add(Admin admin) {
        boolean isExist = this.emailExist(admin.getEmail()).isSuccess();
        if (isExist) {
            return new ResponseBean(false, CommonErrorEnum.REPEAT_EMAIL);
        }
        //对密码加密保存
        admin.setPassword(MD5Util.md5(admin.getPassword(), this.md5Key));
        int result = adminService.addAdmin(admin);
        if (result > 0) {
            return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
        }
        return new ResponseBean(false, CommonErrorEnum.FAILED_QUESTION);
    }


}
