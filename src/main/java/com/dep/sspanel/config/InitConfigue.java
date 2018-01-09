package com.dep.sspanel.config;

import java.util.TimeZone;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * spring初始化完成后执行
 * @author Sherlock
 *
 */
@Component
public class InitConfigue implements ApplicationListener<ContextRefreshedEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
            JSON.defaultTimeZone=TimeZone.getDefault();
        }
    }

}
