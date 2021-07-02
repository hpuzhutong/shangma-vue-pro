package com.zhu.sm.common.util.sendMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/2 21:21
 * @className: SendMailService
 * @description: 发送邮件
 */

@Component
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     *
     * @param to    收件人
     * @param contentStr   发送的内容
     * @throws MessagingException
     */
    public void sendMail(String to, String contentStr) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        try {
            helper.setFrom("朱大桐<lfox20_22@163.com>");
            helper.setTo(to);
            helper.setSubject("工资明细表");
            helper.setText(contentStr,true);   //发送的模板是html
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
