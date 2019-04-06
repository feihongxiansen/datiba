package com.dtb;

import com.dtb.entity.User;
import com.dtb.home.controller.UserController;
import com.dtb.home.dao.UserMapper;
import com.dtb.home.service.UserService;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 1:59 2019/3/10.
 * @ModifyBy：
 */
public class UserServiceTest extends DtbApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserController userController;
    @Test
    public void getUserListTest() {
        System.out.println(userMapper.selectByPrimaryKey(1));
        System.out.println(userService.findUserInfoById((1)));
    }

    @Test
    public void registerTest() throws Exception {
        String[] nameArr = this.toArrayByFileReader1("D:\\dtb\\name.txt").get(0).split(",");
        String[] nickArr = this.toArrayByFileReader1("D:\\dtb\\nick.txt").get(0).split(",");
        User user = new User();
        for (int i = 2; i < 200; i++) {
            user.setId(null);
            user.setPassword("123456");
            user.setUserName(nameArr[i]);
            user.setNickName(nickArr[i]);
            user.setPhone(RandomValueUtil.getTelephone());
            user.setEmail(RandomValueUtil.getEmail(3, 12));
            user.setEmailVerify(Math.random() > 0.5);
            user.setCommentState(Math.random() > 0.5);
            user.setLoginState(Math.random() > 0.5);
            user.setIntegral(RandomValueUtil.getNum(0, 50000));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            user.setLoginTime(formatter.parse(this.randomDate()));
            user.setCreateTime(formatter.parse(this.randomDate()));
            user.setUpdateTime(formatter.parse(this.randomDate()));
            user.setPhoneVerify(Math.random() > 0.5);
            user.setQuestionState(Math.random() > 0.5);
            user.setSex(Math.random() > 0.5 ? (byte) 1 : (byte) 2);
            user.setUserType(Math.random() > 0.5 ? (byte) 1 : (byte) 2);
            user.setAnswerState(Math.random() > 0.5);
            // 打开注释就会插入数据库
            //userController.register(user,null);
            System.out.println(i + "【插入成功】" + user.toString());
        }
    }

    @Test
    public void userUpdateTest() {

    }

    /**
     * 根据文字获取拼音字符串
     *
     * @param text 文字
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 23:24
     */
    private String getPinYinByWord(String text) {
        StringBuffer result = new StringBuffer();
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
        for (Pinyin item : pinyinList) {
            result.append(item.getPinyinWithoutTone());
        }
        return result.toString();
    }

    /**
     * 读取文件到内存
     *
     * @param name 文件路径
     * @return java.util.List<java.lang.String>
     * @author lmx
     * @date 2019/4/6 23:25
     */
    private List<String> toArrayByFileReader1(String name) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回数组
        return arrayList;
    }

    /**
     * 随机生成日期
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 23:31
     */
    private String randomDate() {
        Random rndYear = new Random();
        int year = rndYear.nextInt(10) + 2010;
        Random rndMonth = new Random();
        int month = rndMonth.nextInt(12) + 1;
        Random rndDay = new Random();
        int Day = rndDay.nextInt(30) + 1;
        Random rndHour = new Random();
        int hour = rndHour.nextInt(23);
        Random rndMinute = new Random();
        int minute = rndMinute.nextInt(60);
        Random rndSecond = new Random();
        int second = rndSecond.nextInt(60);
        return year + "-" + cp(month) + "-" + cp(Day) + " " + cp(hour) + ":" + cp(minute) + ":" + cp(second);
    }

    private String cp(int num) {
        String Num = num + "";
        if (Num.length() == 1) {
            return "0" + Num;
        } else {
            return Num;
        }
    }
}
