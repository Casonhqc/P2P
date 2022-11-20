package Cason.mgrsite.listener;

import Cason.p2p.base.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 初始化超级管理员的监听器
 * 实现的是Spring监听器接口
 * 泛型的event代表的是容器启动事件
 * @author Cason
 * @date 2022-11-11 15:24
 */
@Component
public class initAdminListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ILogininfoService logininfoService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Sring容器启动"+ this.logininfoService);
        logininfoService.initAdmin();
    }
}
