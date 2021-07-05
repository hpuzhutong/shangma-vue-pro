package com.zhu.sm.controller;

import com.github.pagehelper.PageHelper;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.common.util.sendMail.SendMailService;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.controller.base.BaseController;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.dto.CornBeanDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.entity.CornBean;
import com.zhu.sm.mapper.AdminMapper;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.service.BrandService;
import com.zhu.sm.service.CornBeanService;
import com.zhu.sm.transfer.BrandTransfer;
import com.zhu.sm.transfer.CornBeanTransfer;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:18
 * @className: BrandController
 * @description:
 */
@RestController
@RequestMapping("corn")

public class CornBeanController extends BaseController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private CornBeanService cornBeanService;

    @Autowired
    private CornBeanTransfer cornBeanTransfer;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private SendMailService sendMailService;

    private Map<Long,ScheduledFuture> map = new HashMap<>();

    /**
     * 发邮件
     */
    @PostConstruct  //在构造函数执行后 此方法立马执行
    public void groupSendEmailToAdmin(){
        List<CornBean> cornBeans = cornBeanService.findAll();
        cornBeans.forEach(cornBean -> {
            String cornExpress = cornBean.getCornExpress();
            CronTrigger cronTrigger = new CronTrigger(cornExpress);
            ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    //发邮件
                    List<Admin> admins = adminMapper.selectList(null);
                    admins.forEach(admin -> {
                        sendMailService.sendMail(admin);
                    });
                }
            }, cronTrigger);
            map.put(cornBean.getCornId(),scheduledFuture);
        });
    }

    /**
     * 暂停定时器
     */
    @PutMapping("pause/{id}")
    public AxiosResult<Void> pauseCorn(@PathVariable Long id){
        ScheduledFuture scheduledFuture = map.get(id);
        if (scheduledFuture!=null && !scheduledFuture.isCancelled()){
            //暂停
            scheduledFuture.cancel(false);
            //删除map
            map.remove(id);
        }
        return AxiosResult.success();
    }

    /**
     * 启动定时器
     */
    @PutMapping("play/{id}")
    public AxiosResult<Void> playCorn(@PathVariable Long id){
        CornBean cornBean = cornBeanService.findById(id);
        ScheduledFuture scheduledFuture  = threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                //重启发邮件
                List<Admin> admins = adminMapper.selectList(null);
                admins.forEach(admin -> {
                    sendMailService.sendMail(admin);
                });
            }
        }, new CronTrigger(cornBean.getCornExpress()));
        map.put(cornBean.getCornId(),scheduledFuture);
        return AxiosResult.success();
    }


    @GetMapping
    public AxiosResult<List<CornBeanDTO>> findAll() {
        return AxiosResult.success(cornBeanTransfer.toDTO(cornBeanService.findAll()));
    }

    @GetMapping("{id}")
    public AxiosResult<CornBeanDTO> findById(@PathVariable long id) {
        CornBean cornBean = cornBeanService.findById(id);
        return AxiosResult.success(cornBeanTransfer.toDTO(cornBean));
    }

    @PostMapping
    public AxiosResult addBrand(@RequestBody CornBean cornBean) {
        return toAxios(cornBeanService.addCorm(cornBean));
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {

        //暂停任务
        ScheduledFuture scheduledFuture = map.get(id);
        if (scheduledFuture!=null && !scheduledFuture.isCancelled()){
            scheduledFuture.cancel(true);
        }
        map.remove(id);
        return toAxios(cornBeanService.deleteById(id));
    }


    @PutMapping
    public AxiosResult<Void> updateById(@RequestBody CornBean cornBean) {
        //把之前的定时器 暂停任务 并 删除
        Long cornId = cornBean.getCornId();
        ScheduledFuture scheduledFuture = map.get(cornId);
        if (scheduledFuture!=null && !scheduledFuture.isCancelled()){
            scheduledFuture.cancel(false);
        }
        map.remove(cornId);
        //将前端表单的新定时器任务加入  并启动
        int row = cornBeanService.updateCorn(cornBean);
        ScheduledFuture schedule = threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                List<Admin> admins = adminMapper.selectList(null);
                admins.forEach(admin -> {
                    sendMailService.sendMail(admin);
                });
            }
        }, new CronTrigger(cornBean.getCornExpress()));
        map.put(cornId,schedule);
        return toAxios(row);
    }



}
