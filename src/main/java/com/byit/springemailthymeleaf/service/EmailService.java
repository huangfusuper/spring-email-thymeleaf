package com.byit.springemailthymeleaf.service;

import com.byit.springemailthymeleaf.pojo.EmailData;

import java.util.List;

/**
 * @author huangfu
 */
public interface EmailService {
    /**
     * 发送邮件
     * @param emailDatas 邮件数据
     * @return 成功信息
     */
    String sendEmail(List<EmailData> emailDatas);
}
