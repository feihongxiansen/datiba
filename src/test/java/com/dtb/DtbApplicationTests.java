package com.dtb;

import com.dtb.utils.email.EmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DtbApplicationTests {

	@Test
	public void contextLoads() {


	}

    @Autowired
    private EmailUtil mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private Environment environment;

    @Test
    public void getEnvironment(){
        System.out.println(environment.getProperty("com.dtb.mail.nickname"));
    }

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("tjpu_feihong@163.com","这是一封简单邮件","大家好，这是我的第一封邮件！");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("tjpu_feihong@163.com","这是一封HTML邮件",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="C:\\bqs\\分期还打包目录\\test\\spring-boot-package-war.war";
        mailService.sendAttachmentsMail("2222@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\bqs\\分期还打包目录\\test\\login-bg.jpg";

        mailService.sendInlineResourceMail("2222@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    /**
     * 按照模板发送邮件
     */
    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        Map<String,Object> map = new HashMap<>();
        map.put("id",123);
        map.put("emailCode",6557);
        //context.setVariable("id", "006");
        context.setVariables(map);
        String emailContent = templateEngine.process("email/account_activation", context);

        mailService.sendHtmlMail("tjpu_feihong@163.com","主题：这是模板邮件",emailContent);
    }

}
