package com.dtb.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @auther: lmx
 * @date: 2019/3/9 16:51
 * @descript: 文件上传与删除工具
 * 其中rootPath获取方式：ResourceUtils.getURL("classpath:").getPath()+"/static/upload/goodsimg";
 */
@Controller
@RequestMapping("/file")
public class FileUploadUtil {

    /**
    * @author lmx
    * @Description 上传文件
    * @Date 10:06 2019/1/7
    * @MethodName upload
    * @param file
    * @param rootPath
    * @param filePre
    * @return java.lang.String
    **/
	@RequestMapping("/upload")
	@ResponseBody
    public static String upload(MultipartFile file, String rootPath,String filePre) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        // 原始名称
        String originalFileName = file.getOriginalFilename();
        // 新文件名
        int random = (int)(Math.random()*1000000);
        String newFileName = filePre + res + random + originalFileName.substring(originalFileName.lastIndexOf("."));
        // 创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH)+1));

        // 新文件
        File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
        // 判断目标文件所在目录是否存在
        if( !newFile.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        // 将内存中的数据写入磁盘
        file.transferTo(newFile);
        // 完整的url
        String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + newFileName;
        return  fileUrl;
    }

    /**
    * @author lmx
    * @Description 删除文件
    * @Date 10:16 2019/1/7
    * @MethodName delFile
    * @param path
    * @return java.lang.String
    **/
    @RequestMapping("delete")
    @ResponseBody
    public static String delFile(@RequestParam("path") String path) {
        System.out.println("待删除文件地址：" + path);
        String resultInfo = null;
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                resultInfo =  "删除成功";
            } else {
                resultInfo =  "删除失败";
            }
        } else {
            resultInfo = "文件不存在！";
        }
        return resultInfo;
    }


    /**
     * @auther: lmx
     * @date: 2019/3/9 16:48
     * @descript: 上传多文件
     * @param: files
     * @param: rootPath
     * @param: fileNamePre
     * @return: java.lang.String
     */
    public static String uploadFiles(MultipartFile[] files,String rootPath,String fileNamePre)throws IOException{
        //遍历处理文件
        String info = null;
        for (MultipartFile file:files) {
            String s = FileUploadUtil.upload(file,rootPath,fileNamePre);
            System.out.println("上传详情"+s);
            info = ","+s;
        }
        if (info != null)
            info = info.substring(1);
        return info;
    }
}
