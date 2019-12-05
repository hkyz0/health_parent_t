package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TjTask {

    @Reference
    private ReportService reportService;

    //@Scheduled(cron = "1/10 * * * * ?")
    public void doTj(){
        System.out.println(new Date());
        try {
            reportService.doTj();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
