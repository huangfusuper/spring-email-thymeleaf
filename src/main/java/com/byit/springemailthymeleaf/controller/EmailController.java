package com.byit.springemailthymeleaf.controller;

import com.byit.springemailthymeleaf.pojo.EmailData;
import com.byit.springemailthymeleaf.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author Administrator
 */
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("sendMsg")
    public void sendMsg (){
        EmailData build = EmailData.builder().consuming("20秒")
                .endDate("2020年5月22日17:16:44")
                .startDate("2020年5月21日17:16:57")
                .logPaths(Arrays.asList("http://120.0.0.1:8989","http://120.0.0.1:1231","http://120.0.0.1:3222"))
                .nodeName("test")
                .runStatus("成功")
                .build();

        emailService.sendEmail(Arrays.asList(build));
    }
}
