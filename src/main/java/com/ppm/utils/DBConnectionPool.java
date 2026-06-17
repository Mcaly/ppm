package com.ppm.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionPool {
    private static HikariDataSource dataSource;

    static {
        try {
            // 读取配置文件
            Properties props = new Properties();
            InputStream is = DBConnectionPool.class.getClassLoader()
                    .getResourceAsStream("db.properties");
            props.load(is);

            // HikariCP配置
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("jdbc.url"));
            config.setUsername(props.getProperty("jdbc.username"));
            config.setPassword(props.getProperty("jdbc.password"));
            config.setDriverClassName(props.getProperty("jdbc.driver"));

            // 连接池参数
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("pool.maxSize")));
            config.setMinimumIdle(Integer.parseInt(props.getProperty("pool.minIdle")));
            config.setIdleTimeout(Long.parseLong(props.getProperty("pool.idleTimeout")));
            config.setConnectionTimeout(Long.parseLong(props.getProperty("pool.connectionTimeout")));
            config.setLeakDetectionThreshold(Long.parseLong(props.getProperty("pool.leakDetectionThreshold")));

            // 连接验证
            config.setConnectionTestQuery("SELECT 1");

            // 性能优化
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接池初始化失败", e);
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 关闭连接池
    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    // 获取连接池状态
    public static String getPoolStats() {
        if (dataSource == null || dataSource.isClosed()) {
            return "连接池未初始化或已关闭";
        }
        return String.format("总连接数: %d, 活跃连接数: %d, 空闲连接数: %d, 等待线程数: %d",
                dataSource.getHikariPoolMXBean().getTotalConnections(),
                dataSource.getHikariPoolMXBean().getActiveConnections(),
                dataSource.getHikariPoolMXBean().getIdleConnections(),
                dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection());
    }
}