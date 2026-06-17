package com.ppm.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据库工具类
 * 基于 DBConnectionPool 提供便捷的数据库操作方法
 */
public class DBUtil {

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws SQLException {
        return DBConnectionPool.getConnection();
    }

    /**
     * 关闭资源
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接和语句
     */
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    /**
     * 执行更新操作（INSERT、UPDATE、DELETE）
     * @return 影响的行数
     */
    public static int executeUpdate(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            return pstmt.executeUpdate();
        } finally {
            close(conn, pstmt);
        }
    }

    /**
     * 执行查询操作
     * @return 结果集列表（每行为一个 Map）
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();
            return resultSetToList(rs);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /**
     * 执行查询，返回单个值
     */
    public static Object executeScalar(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> list = executeQuery(sql, params);
        if (list != null && !list.isEmpty()) {
            Map<String, Object> row = list.get(0);
            if (row != null && !row.isEmpty()) {
                return row.values().iterator().next();
            }
        }
        return null;
    }

    /**
     * 执行插入并返回生成的主键
     */
    public static int executeInsertWithKey(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParams(pstmt, params);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /**
     * 设置预编译语句参数
     */
    private static void setParams(PreparedStatement pstmt, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 将 ResultSet 转换为 List<Map>
     */
    private static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            Map<String, Object> row = new java.util.LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                Object value = rs.getObject(i);
                row.put(columnName, value);
            }
            list.add(row);
        }
        return list;
    }
}
