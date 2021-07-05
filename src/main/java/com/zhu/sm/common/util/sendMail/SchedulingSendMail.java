package com.zhu.sm.common.util.sendMail;

import com.zhu.sm.entity.Admin;
import com.zhu.sm.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/2 22:03
 * @className: SendMail
 * @description: 此方法不能做到动态的定时任务
 */

@Component
public class SchedulingSendMail {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private SendMailService sendMailService;

    /*
     * 群发邮件
     *
     * cron = "0 22 22 2 7 *"  表示 "秒 分 时 日 月 周"
     *
     * */
//    @Scheduled(cron = "0 0 14 15 * *")  //每个月15号下午14点00发邮件
    public void groupSendEmail() {
        List<Admin> admins = adminMapper.selectList(null);
        admins.forEach(admin -> {
            // sendMailService 里面使用的是异步操作
            // 相当于在一个线程中又开了一个子线程
            sendMailService.sendMail(admin);
        });

    }


}
