package com.dtb.admin.controller;

import com.dtb.admin.service.GiftService;
import com.dtb.entity.GiftWithBLOBs;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/6-1:42
 */
@Controller("giftAdminController")
@RequestMapping("/admin/gift")
public class GiftController {

    @Autowired
    private GiftService giftService;
    @Value("${com.dtb.file.baseFilePath}")
    private String baseFilePath;

    /**
     * 物品列表页渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 0:20
     */
    @RequestMapping("/listPage")
    public String listPage() {
        return "admin/gift/list";
    }

    /**
     * 多条件分页查询
     *
     * @param queryParam 搜索参数
     * @param pageNum    当前页码
     * @param pageSize   每页大小
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/6 0:19
     */
    @RequestMapping("/getPageList/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getPageList(GiftWithBLOBs queryParam,
                                    @PathVariable Integer pageNum,
                                    @PathVariable Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<GiftWithBLOBs> pageList = giftService.selectPageList(queryParam);

        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pages", pageList.getPages());
        pageInfo.put("total", pageList.getTotal());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("giftList", pageList);
        resultMap.put("pageInfo", pageInfo);

        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id批量更新
     *
     * @param idList id数组
     * @param param  更新参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/1 23:05
     */
    @RequestMapping("/updateBatchByIds")
    @ResponseBody
    public ResponseBean updateBatchByIds(@RequestParam List<Integer> idList, GiftWithBLOBs param) {
        giftService.updateBatchByIds(idList, param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 添加轮播图页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 0:58
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/gift/add";
    }

    /**
     * 添加物品
     *
     * @param param 参数
     * @param file  文件
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/6 1:12
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseBean add(GiftWithBLOBs param, @RequestParam MultipartFile file) throws Exception {
        ResponseBean uploadResult = this.uploadCarousel(file);
        param.setPhotos((String) uploadResult.getData());
        giftService.insert(param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 上传用户头像
     *
     * @param file 图片文件
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/17 1:37
     */
    @RequestMapping("/uploadCarousel")
    @ResponseBody
    public ResponseBean<String> uploadCarousel(@RequestParam("file") MultipartFile file) throws Exception {
        String uploadPath = "/upload/system/gift";
        String rootPath = this.baseFilePath + uploadPath;
        String imgPath = FileUploadUtil.upload(file, rootPath, "gift_");
        imgPath = "/file" + uploadPath + "/" + imgPath;
        return new ResponseBean<String>(true, imgPath, CommonErrorEnum.FILEUPLOAD_SUCCESS);
    }

    /**
     * 修改轮播图信息页面渲染
     *
     * @param id    id
     * @param model 视图容器
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 1:24
     */
    @RequestMapping("/editPage/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("info", giftService.findById(id));
        return "admin/gift/edit";
    }

    /**
     * 根据id修改
     *
     * @param param 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/6 1:25
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseBean edit(GiftWithBLOBs param) {
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(param.getId());
        giftService.updateBatchByIds(idList, param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}
