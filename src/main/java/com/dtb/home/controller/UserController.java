package com.dtb.home.controller;

import com.dtb.entity.User;
import com.dtb.home.service.UserService;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.MD5Util;
import com.dtb.utils.VerifyUtil;
import com.dtb.utils.email.EmailUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 14:56 2019/2/25.
 * @ModifyBy：
 */
@Controller("userController")
@RequestMapping("home/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Environment environment;
    @Autowired
    private EmailUtil emailUtil;
    @Value("${com.dtb.file.baseFilePath}")
    private String baseFilePath;


    /**
     * @auther: lmx
     * @date: 2019/3/1 15:35
     * @descript: 用户登录验证
     * @param: phone 手机号码
     * @param: password 登录密码
     * @param: verifyCode 验证码
     * @param: pagePath 登录后页面跳转路径
     * @param: session 会话session
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> checkLogin(@RequestParam(value = "email", required = true) String email,
                                                    @RequestParam(value = "password", required = true) String password,
                                                    @RequestParam(value = "verifyCode", required = true) String verifyCode,
                                                    @RequestParam(value = "pagePath", required = false, defaultValue = "/home/index/index") String pagePath,
                                                    HttpSession session) {

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
        User user = userService.findByEmail(email);
        //判断是否存在当前用户
        if (user == null) {
            return new ResponseBean(false, CommonErrorEnum.NO_USER_EXIST);
        }

        //判断密码是否正确
        if (!MD5Util.md5Verify(password, environment.getProperty("com.dtb.security.md5.key"), user.getPassword())) {
            return new ResponseBean(false, CommonErrorEnum.INVALID_PASSWORD);
        }

        //判断是否通过邮箱验证
        if (!user.getEmailVerify()) {
            return new ResponseBean(false, CommonErrorEnum.NO_VERIFY_EMAIL);
        }

        //判断用户当前是否被禁止登录
        if (!user.getLoginState()) {
            return new ResponseBean(false, CommonErrorEnum.BAN_LOGIN);
        }

        //通过所有验证
        session.setAttribute("user", user);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pagePath", pagePath);
        ResponseBean responseBean = new ResponseBean(true, resultMap, CommonErrorEnum.LOGIN_SUCCESS);
        return responseBean;
    }

    /**
     * @auther: lmx
     * @date: 2019/3/1 16:12
     * @descript: 退出登录
     * @param: session 会话session
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("logOut")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> logOut(HttpSession session) {
        session.removeAttribute("user");
        return new ResponseBean(true, "/home/index/index", CommonErrorEnum.LOGOUT_SUCCESS);
    }


    /**
     * @auther: lmx
     * @date: 2019/3/1 18:40
     * @descript: 检测邮箱地址是否可用
     * @param: email 邮箱地址
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("checkEmailExist")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> checkEmailExist(@RequestParam(value = "email", required = true) String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseBean(false, CommonErrorEnum.NOT_EXIST_EMAIL);
        }
        return new ResponseBean(true, CommonErrorEnum.REPEAT_EMAIL);
    }


    /**
     * @param user 用户信息
     * @param file 头像文件
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/17 12:52
     * @descript 注册用户
     */
    @RequestMapping("register")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> register(User user, @RequestParam("file") MultipartFile file) throws Exception {

        //判断邮箱地址是否可用
        if (this.checkEmailExist(user.getEmail()).isSuccess()) {
            ResponseBean<CommonErrorEnum> checkRes = this.checkEmailExist(user.getEmail());
            checkRes.setSuccess(false);
            return checkRes;
        }

        ResponseBean uploadResult = this.uploadUserPhoto(file);
        user.setUserPhoto((String) uploadResult.getData());

        //重新生成邮箱验证码
        user.setEmailCode((String) VerifyUtil.createImage()[0]);
        //对用户密码加密
        user.setPassword(MD5Util.md5(user.getPassword(), environment.getProperty("com.dtb.security.md5.key")));
        //用户信息插入数据库
        userService.createUser(user);


        //创建用户失败
        if (user.getId() == null) {
            return new ResponseBean(false, CommonErrorEnum.FAILED_CREATEUSER);
        }

        //发送邮件，让用户激活账户
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("emailCode", user.getEmailCode());
        emailUtil.sendTemplateMail(user.getEmail(), "【答题吧-账号激活】", map, "email/account_activation");

        return new ResponseBean(true, "/home/user/login", CommonErrorEnum.WAIT_VERIFY_EMAIL);
    }

    /**
     * @auther: lmx
     * @date: 2019/3/2 17:49
     * @descript: 验证用户邮箱，激活账号
     * @param: id 用户id
     * @param: emailCode 邮箱验证码
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("activation/{id}/{emailCode}")
    public String activation(@PathVariable Integer id, @PathVariable String emailCode, Model model) {
        User user = userService.findById(id);
        String page = "home/activation";

        //检测用户是否存在
        if (user == null) {
            model.addAttribute("data", new ResponseBean(false, CommonErrorEnum.NO_USER_EXIST));
            return page;
        }

        //检测是否已经验证过邮箱
        if (user.getEmailVerify()) {
            model.addAttribute("data", new ResponseBean(true, CommonErrorEnum.VERIFYED_EMAIL));
            return page;
        }

        //验证邮箱验证码是否正确
        if (!user.getEmailCode().equalsIgnoreCase(emailCode)) {
            model.addAttribute("data", new ResponseBean<Integer>(false, id, CommonErrorEnum.ERROR_EMAILCODE));
            return page;
        }

        //通过验证
        user.setEmailVerify(true);
        int res = userService.updateByIdSelective(user);

        //验证结果是否持久化
        if (res <= 0) {
            model.addAttribute("data", new ResponseBean<Integer>(false, id, CommonErrorEnum.FAILED_VERIFY));
            return page;
        }

        model.addAttribute("data", new ResponseBean(true, CommonErrorEnum.SUCCESS_VERIFY_EMAIL));
        return page;
    }

    /**
     * @auther: lmx
     * @date: 2019/3/3 0:11
     * @descript: 重新发送激活邮件
     * @param: id 用户id
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("resetEmailCode/{id}")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> resetEmailCode(@PathVariable Integer id) {
        User user = userService.findById(id);

        //检测用户是否存在
        if (user == null) {
            return new ResponseBean(false, CommonErrorEnum.NO_USER_EXIST);
        }

        //检测是否已经验证过邮箱
        if (user.getEmailVerify()) {
            return new ResponseBean(false, CommonErrorEnum.VERIFYED_EMAIL);
        }

        //重新生成邮箱验证码
        user.setEmailCode((String) VerifyUtil.createImage()[0]);

        //将新的邮箱验证码持久化
        int res = userService.updateByIdSelective(user);
        //检测是否持久化成功
        if (res <= 0) {
            return new ResponseBean(false, CommonErrorEnum.FAILED_SENDEMAIL);
        }

        //发送邮件，让用户激活账户
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("emailCode", user.getEmailCode());
        emailUtil.sendTemplateMail(user.getEmail(), "【答题吧-账号激活】", map, "email/account_activation");

        return new ResponseBean(true, CommonErrorEnum.SENDEMAIL_SUCCESS);
    }

    /**
     * @param
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/10 17:43
     * @descript 获取用户列表，返回字段仅有用户id，用户名和昵称
     */
    @RequestMapping("getUserList")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> getUserList() {
        List<User> userList = userService.findUserList();
        return new ResponseBean(true, userList, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param userType
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/10 23:02
     * @descript 根据用户类型获取用户列表
     */
    @RequestMapping("getUserListToLimit/{pageNum}/{pageSize}/{userType}")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> getUserListToLimit(@PathVariable Integer pageNum,
                                                            @PathVariable Integer pageSize,
                                                            @PathVariable Byte userType) {
        PageHelper.startPage(pageNum, pageSize);

        Page<User> userPage = userService.findUserListToLimit(userType);
        //获取分页信息
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", userPage.getPageSize());
        pageInfo.put("pageNum", userPage.getPageNum());
        pageInfo.put("pages", userPage.getPages());
        pageInfo.put("total", userPage.getTotal());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("userList", userPage);
        resultMap.put("pageInfo", pageInfo);

        return new ResponseBean(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * @param file 图片文件
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/17 1:37
     * @descript 上传用户头像
     */
    @RequestMapping("uploadUserPhoto")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> uploadUserPhoto(@RequestParam("file") MultipartFile file) throws Exception {
        String uploadPath = "/upload/images/avatar";
        String rootPath = this.baseFilePath + uploadPath;
        String imgPath = FileUploadUtil.upload(file, rootPath, "avatar_");
        imgPath = "/file" + uploadPath + "/" + imgPath;
        return new ResponseBean(true, imgPath, CommonErrorEnum.FILEUPLOAD_SUCCESS);
    }
}
