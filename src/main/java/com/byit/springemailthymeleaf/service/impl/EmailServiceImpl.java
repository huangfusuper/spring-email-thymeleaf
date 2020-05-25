package com.byit.springemailthymeleaf.service.impl;

import com.byit.springemailthymeleaf.pojo.EmailData;
import com.byit.springemailthymeleaf.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件信息
 * @author huangfu
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.properties.from}")
    private String from;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendEmail(List<EmailData> emailDatas) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo("xxxxx@xxxxx.com");
            mimeMessageHelper.setSubject("测试邮件");
            Map<String,Object> variables = new HashMap<>(8);
            variables.put("title","xxxxxxx");
            variables.put("env","mysql_dev0000");
            variables.put("flowConsumeDate","1天");
            variables.put("data",emailDatas);
            Context context = new Context();
            context.setVariables(variables);
            String process = templateEngine.process("/mail/mail", context);
            mimeMessageHelper.setText(process,true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
