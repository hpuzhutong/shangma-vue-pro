package com.zhu.sm.common.util.sendMail;

import com.zhu.sm.entity.Admin;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 发邮件的真正操作
     *
     * @param admin
     * @throws IOException
     * @throws TemplateException
     * @throws MessagingException
     */
    @Async
    public void sendMail(Admin admin) {

        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = null;
        try {
            template = configuration.getTemplate("salary.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<>();
        map.put("adminAccount", admin.getAdminAccount());
        map.put("adminPhone", admin.getAdminPhone());
        map.put("adminName", admin.getAdminName());
        map.put("adminSalary", admin.getAdminSalary() + "");

        StringWriter stringWriter = new StringWriter();
        try {
            template.process(map, stringWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            helper.setFrom("朱桐<2464821976@qq.com>");
            helper.setTo(admin.getAdminEmail());
            helper.setSubject("工资明细表");
            helper.setText(stringWriter.toString(), true);   //发送的模板是html
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
