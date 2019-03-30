package com.dtb.utils;

import org.springframework.util.DigestUtils;

/**
 * @Author：lmx
 * @Description：md5加密
 * @Date：Created on 22:59 2019/2/28.
 * @ModifyBy：
 */
public class MD5Util {

    /**
     * @auther: lmx
     * @descript: md5加密，有秘钥加密
     * @date: 2019/3/1 0:00
     * @param: [text, key]
     * @return: java.lang.String
     */
    public static String md5(String text, String key) {
        //加密后的字符串
        String encodeStr= DigestUtils.md5DigestAsHex((text + key).getBytes());
//        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    /**
     * @auther: lmx
     * @descript: md5加密，无秘钥加密
     * @date: 2019/3/1 0:00
     * @param: [text]
     * @return: java.lang.String
     */
    public static String md5(String text) {
        //加密后的字符串
        String encodeStr= DigestUtils.md5DigestAsHex((text).getBytes());
//        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    /**
     * @auther: lmx
     * @descript: MD5验证方法，含秘钥
     * @date: 2019/3/1 0:03
     * @param: [text, key, md5]
     * @return: boolean
     */
    public static boolean md5Verify(String text, String key, String md5) {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if(md5Text.equalsIgnoreCase(md5))
        {
            return true;
        }

        return false;
    }

    /**
     * @auther: lmx
     * @descript: MD5验证方法，不含秘钥
     * @date: 2019/3/1 0:15
     * @param: [text, md5]
     * @return: boolean
     */
    public static boolean md5Verify(String text, String md5){
        //根据传入的密钥进行验证
        String md5Text = md5(text);
        if(md5Text.equalsIgnoreCase(md5))
        {
//            System.out.println("MD5验证通过");
            return true;
        }

        return false;
    }
}
