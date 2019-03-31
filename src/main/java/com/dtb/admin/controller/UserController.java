package com.dtb.admin.controller;

import com.dtb.admin.service.UserService;
import com.dtb.entity.User;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/31-9:45
 */
@RequestMapping("/admin/user")
@Controller("userAdminController")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户列表
     *
     * @param queryParam 查询参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 9:55
     */
    @RequestMapping("/getUserPageList/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getUserPageList(@PathVariable Integer pageNum,
                                        @PathVariable Integer pageSize,
                                        User queryParam) {
        PageHelper.startPage(pageNum, pageSize);
        Page<User> userPage = userService.selectPageUserList(queryParam);
        //获取分页信息
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", userPage.getPageSize());
        pageInfo.put("pageNum", userPage.getPageNum());
        pageInfo.put("pages", userPage.getPages());
        pageInfo.put("total", userPage.getTotal());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("userList", userPage);
        resultMap.put("pageInfo", pageInfo);

        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 用户列表界面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/31 11:08
     */
    @RequestMapping("/userListPage")
    public String userListPage() {
        return "/admin/user/list";
    }

    /**
     * 模糊搜索用户信息
     *
     * @param pageNum    当前页码
     * @param pageSize   每页显示条数
     * @param queryParam 精确查询参数
     * @param vagueParam 模糊查询参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 12:06
     */
    @RequestMapping("/getUserPageListVague/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getUserPageListVague(@PathVariable Integer pageNum,
                                             @PathVariable Integer pageSize,
                                             @RequestParam String vagueParam,
                                             User queryParam) {
        PageHelper.startPage(pageNum, pageSize);
        Page<User> userPage = userService.selectPageUserListVague(queryParam, vagueParam);
        //获取分页信息
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", userPage.getPageSize());
        pageInfo.put("pageNum", userPage.getPageNum());
        pageInfo.put("pages", userPage.getPages());
        pageInfo.put("total", userPage.getTotal());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("userList", userPage);
        resultMap.put("pageInfo", pageInfo);

        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id修改用户信息
     *
     * @param user 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 14:42
     */
    @RequestMapping("/updateUserById")
    @ResponseBody
    public ResponseBean updateUserById(User user) {
        if (user == null) {
            return new ResponseBean(false, CommonErrorEnum.BAD_REQUEST);
        }
        userService.updateByIdSelective(user);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 编辑用户信息页面
     *
     * @param id 用户ID
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/31 15:14
     */
    @RequestMapping("/editPage/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/admin/user/edit";
    }

    /**
     * 批量操作
     *
     * @param idList 批量软删除
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 16:32
     */
    @RequestMapping("updateBatchByIds")
    @ResponseBody
    public ResponseBean updateBatchByIds(@RequestParam List<Integer> idList,
                                         User param) {
        int result = userService.updateBatchByIds(idList, param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}
