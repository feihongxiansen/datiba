package com.dtb.utils.email.impl;

import com.dtb.utils.email.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @Author：lmx
 * @Description：邮箱操作相关类
 * @Date：Created on 22:45 2019/3/1.
 * @ModifyBy：
 */
@Component
public class EmailUtilImpl implements EmailUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${com.dtb.mail.nickname}")
    private String nickName;

    private InternetAddress from;

    public InternetAddress getFrom() {

        try {
            nickName = javax.mail.internet.MimeUtility.encodeText(nickName);
            from = new InternetAddress(nickName+" <"+username+">");
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.from;
    }

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setFrom(this.getFrom());
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            //helper.setCc("xxx@163.com");//抄送
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.getFrom());
            helper.setSubject(subject);
            helper.setTo(to);
            //有第二个参数true表示发送HTML邮件，否则HTML代码不会被编译
            helper.setText(content,true);
            //helper.setCc("xxx@163.com");//抄送
            mailSender.send(message);
            logger.info("html邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("html邮件发送出现异常。");
            e.printStackTrace();
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.getFrom());
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            //helper.setCc("xxx@163.com");//抄送
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName,file);
            mailSender.send(message);
            logger.info("带附件邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("带附件邮件发送出现异常。");
            e.printStackTrace();
        }
    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.getFrom());
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content);
            //helper.setCc("xxx@163.com");//抄送
            //添加附件
            FileSystemResource file = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,file);
            mailSender.send(message);
            logger.info("带静态资源文件邮件已经发送。");
        } catch (MessagingException e) {
            logger.info("带静态资源文件邮件发送出现异常。");
            e.printStackTrace();
        }
    }

    @Override
    public void sendTemplateMail(String to,String subject,Map<String, Object> paramsMap, String templateName) {
        //创建邮件正文
        Context context = new Context();
        context.setVariables(paramsMap);
        String emailContent = templateEngine.process(templateName, context);
        this.sendHtmlMail(to,subject,emailContent);
    }
}
