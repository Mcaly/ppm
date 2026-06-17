package com.ppm.listener;

import com.ppm.utils.DBConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("========================================");
        System.out.println("应用启动成功！");
        System.out.println("数据库连接池已初始化");
        System.out.println(DBConnectionPool.getPoolStats());
        System.out.println("========================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("========================================");
        System.out.println("应用正在关闭，释放数据库连接池...");
        DBConnectionPool.closePool();
        System.out.println("连接池已关闭");
        System.out.println("========================================");
    }
}