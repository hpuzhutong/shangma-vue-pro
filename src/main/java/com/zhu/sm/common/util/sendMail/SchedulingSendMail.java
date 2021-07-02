package com.zhu.sm.common.util.sendMail;

import com.zhu.sm.entity.Admin;
import com.zhu.sm.mapper.AdminMapper;
import com.zhu.sm.mapper.AdminRoleMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/2 22:03
 * @className: SendMail
 * @description:
 */

@Component
public class SchedulingSendMail {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /*
    * 群发邮件
    *
    * cron = "0 22 22 2 7 *"  表示 "秒 分 时 日 月 周"
    *
    * */
    @Scheduled(cron = "0 22 22 2 7 *")
    public void groupSendEmail() {
        List<Admin> admins = adminMapper.selectList(null);
        admins.forEach(admin -> {
            try {
                Configuration configuration = freeMarkerConfigurer.getConfiguration();
                Template template = configuration.getTemplate("salary.ftl");

                Map<String, String> map = new HashMap<>();
                map.put("adminAccount", admin.getAdminAccount());
                map.put("adminPhone", admin.getAdminPhone());
                map.put("adminName", admin.getAdminName());
                map.put("adminSalary", admin.getAdminSalary() + "");

                StringWriter stringWriter = new StringWriter();
                template.process(map,stringWriter);

                sendMailService.sendMail(admin.getAdminEmail(),stringWriter.toString());

            } catch (Exception e) {

            }

        });

    }


}
