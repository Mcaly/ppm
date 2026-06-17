<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ppm.entity.User" %>
<%
    String path = request.getContextPath();
    String currentUri = request.getRequestURI();

    // 获取当前登录用户
    User loginUser = (User) session.getAttribute("loginUser");
    String currentUsername = loginUser != null ? loginUser.getRealName() : "";
    int jobType = loginUser != null ? loginUser.getJobType() : 0; // 1=管理员 2=收费员 3=维修师傅 4=保安

    // 判断当前激活菜单
    String activeMenu = "";
    if (currentUri.contains("/user/")) activeMenu = "user";
    else if (currentUri.contains("/building/")) activeMenu = "building";
    else if (currentUri.contains("/house/")) activeMenu = "house";
    else if (currentUri.contains("/owner/")) activeMenu = "owner";
    else if (currentUri.contains("/fee/")) activeMenu = "fee";
    else if (currentUri.contains("/repair/")) activeMenu = "repair";
    else if (currentUri.contains("/car/")) activeMenu = "car";
    else if (currentUri.contains("/notice/")) activeMenu = "notice";
    else if (currentUri.contains("/index")) activeMenu = "index";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.title} - 物业管理系统</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Microsoft YaHei', sans-serif;
            background: #f0f2f5;
            min-height: 100vh;
        }

        /* 顶部导航 */
        .header {
            background: #001529;
            color: #fff;
            height: 60px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 24px;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            z-index: 1000;
        }

        .header-logo {
            font-size: 20px;
            font-weight: bold;
        }

        .header-right {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .header-user {
            color: #fff;
            font-size: 14px;
        }

        .header-logout {
            color: #fff;
            text-decoration: none;
            font-size: 14px;
            padding: 4px 12px;
            border: 1px solid rgba(255,255,255,0.5);
            border-radius: 4px;
            transition: all 0.3s;
        }

        .header-logout:hover {
            background: rgba(255,255,255,0.1);
            border-color: #fff;
        }

        /* 侧边栏 */
        .sider {
            position: fixed;
            top: 60px;
            left: 0;
            bottom: 0;
            width: 200px;
            background: #001529;
            overflow-y: auto;
        }

        .sider-menu {
            list-style: none;
            padding: 10px 0;
        }

        .sider-menu li a {
            display: flex;
            align-items: center;
            padding: 12px 24px;
            color: rgba(255,255,255,0.65);
            text-decoration: none;
            font-size: 14px;
            transition: all 0.3s;
        }

        .sider-menu li a:hover {
            color: #fff;
            background: rgba(255,255,255,0.1);
        }

        .sider-menu li a.active {
            color: #fff;
            background: #1890ff;
        }

        .sider-menu li a .icon {
            margin-right: 10px;
            font-size: 16px;
        }

        /* 主内容区 */
        .main {
            margin-left: 200px;
            margin-top: 60px;
            padding: 24px;
            min-height: calc(100vh - 60px);
        }

        .page-header {
            margin-bottom: 24px;
        }

        .page-header h2 {
            font-size: 20px;
            color: #333;
        }

        .content-card {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.1);
            padding: 24px;
        }

        /* 通用表格样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table th, table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #f0f0f0;
        }

        table th {
            background: #fafafa;
            font-weight: 500;
            color: #333;
        }

        table tbody tr:hover {
            background: #f5f5f5;
        }

        /* 通用按钮样式 */
        .btn {
            display: inline-block;
            padding: 6px 16px;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            text-decoration: none;
            border: 1px solid #d9d9d9;
            background: #fff;
            color: #333;
            transition: all 0.3s;
        }

        .btn:hover {
            border-color: #1890ff;
            color: #1890ff;
        }

        .btn-primary {
            background: #1890ff;
            border-color: #1890ff;
            color: #fff;
        }

        .btn-primary:hover {
            background: #40a9ff;
            border-color: #40a9ff;
            color: #fff;
        }

        .btn-danger {
            background: #ff4d4f;
            border-color: #ff4d4f;
            color: #fff;
        }

        .btn-danger:hover {
            background: #ff7875;
            border-color: #ff7875;
            color: #fff;
        }

        .btn-sm {
            padding: 2px 8px;
            font-size: 12px;
        }

        /* 通用表单样式 */
        .form-group {
            margin-bottom: 16px;
        }

        .form-group label {
            display: block;
            margin-bottom: 6px;
            font-size: 14px;
            color: #333;
            font-weight: 500;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #d9d9d9;
            border-radius: 6px;
            font-size: 14px;
            outline: none;
            transition: border-color 0.3s;
        }

        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            border-color: #1890ff;
            box-shadow: 0 0 0 2px rgba(24,144,255,0.2);
        }

        /* 工具栏 */
        .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 16px;
        }

        .search-bar {
            display: flex;
            gap: 10px;
        }

        .search-bar input,
        .search-bar select {
            padding: 6px 12px;
            border: 1px solid #d9d9d9;
            border-radius: 6px;
            font-size: 14px;
            outline: none;
        }

        .search-bar input:focus,
        .search-bar select:focus {
            border-color: #1890ff;
        }

        /* 筛选栏 */
        .filter-bar {
            display: flex;
            gap: 10px;
            margin-bottom: 16px;
            flex-wrap: wrap;
        }

        .filter-bar select,
        .filter-bar input {
            padding: 6px 12px;
            border: 1px solid #d9d9d9;
            border-radius: 6px;
            font-size: 14px;
            outline: none;
        }

        /* 分页 */
        .pagination {
            display: flex;
            justify-content: flex-end;
            gap: 8px;
            margin-top: 16px;
        }

        .pagination a {
            padding: 6px 12px;
            border: 1px solid #d9d9d9;
            border-radius: 4px;
            text-decoration: none;
            color: #333;
            font-size: 13px;
        }

        .pagination a:hover {
            border-color: #1890ff;
            color: #1890ff;
        }

        .pagination a.active {
            background: #1890ff;
            border-color: #1890ff;
            color: #fff;
        }

        /* 状态标签 */
        .status-tag {
            display: inline-block;
            padding: 2px 8px;
            border-radius: 4px;
            font-size: 12px;
        }

        .status-success {
            background: #f6ffed;
            border: 1px solid #b7eb8f;
            color: #52c41a;
        }

        .status-warning {
            background: #fffbe6;
            border: 1px solid #ffe58f;
            color: #faad14;
        }

        .status-error {
            background: #fff2f0;
            border: 1px solid #ffccc7;
            color: #ff4d4f;
        }

        .status-info {
            background: #e6f7ff;
            border: 1px solid #91d5ff;
            color: #1890ff;
        }

        /* 仪表盘卡片 */
        .dashboard-cards {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin-bottom: 24px;
        }

        .stat-card {
            background: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.1);
            display: flex;
            align-items: center;
            gap: 16px;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .stat-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }

        .stat-icon {
            width: 56px;
            height: 56px;
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 28px;
        }

        .stat-icon-blue {
            background: #e6f7ff;
            color: #1890ff;
        }

        .stat-icon-green {
            background: #f6ffed;
            color: #52c41a;
        }

        .stat-icon-orange {
            background: #fff7e6;
            color: #fa8c16;
        }

        .stat-icon-red {
            background: #fff2f0;
            color: #ff4d4f;
        }

        .stat-icon-purple {
            background: #f9f0ff;
            color: #722ed1;
        }

        .stat-icon-cyan {
            background: #e6fffb;
            color: #13c2c2;
        }

        .stat-info {
            flex: 1;
        }

        .stat-title {
            font-size: 14px;
            color: #666;
            margin-bottom: 8px;
        }

        .stat-value {
            font-size: 28px;
            font-weight: 600;
            color: #333;
            line-height: 1;
        }

        .stat-footer {
            font-size: 12px;
            color: #999;
            margin-top: 8px;
        }

        .stat-footer a {
            color: #1890ff;
            text-decoration: none;
        }

        .stat-footer a:hover {
            text-decoration: underline;
        }

        /* 图表卡片 */
        .chart-card {
            background: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }

        .chart-card-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 16px;
            padding-bottom: 12px;
            border-bottom: 1px solid #f0f0f0;
        }

        .chart-card-title {
            font-size: 16px;
            font-weight: 500;
            color: #333;
        }

        .chart-row {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

        /* 列表卡片 */
        .list-card {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }

        .list-card-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 16px 20px;
            border-bottom: 1px solid #f0f0f0;
        }

        .list-card-title {
            font-size: 16px;
            font-weight: 500;
            color: #333;
        }

        .list-card-body {
            padding: 0;
        }

        .list-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 12px 20px;
            border-bottom: 1px solid #f0f0f0;
            transition: background 0.2s;
        }

        .list-item:last-child {
            border-bottom: none;
        }

        .list-item:hover {
            background: #fafafa;
        }

        .list-item-content {
            flex: 1;
        }

        .list-item-title {
            font-size: 14px;
            color: #333;
            margin-bottom: 4px;
        }

        .list-item-desc {
            font-size: 12px;
            color: #999;
        }

        .list-item-time {
            font-size: 12px;
            color: #999;
            white-space: nowrap;
            margin-left: 16px;
        }

        /* 进度条 */
        .progress-bar {
            height: 8px;
            background: #f0f0f0;
            border-radius: 4px;
            overflow: hidden;
            margin-top: 8px;
        }

        .progress-bar-fill {
            height: 100%;
            border-radius: 4px;
            transition: width 0.3s;
        }

        .progress-blue {
            background: #1890ff;
        }

        .progress-green {
            background: #52c41a;
        }

        .progress-orange {
            background: #fa8c16;
        }

        .progress-red {
            background: #ff4d4f;
        }

        /* 快捷操作 */
        .quick-actions {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 16px;
            margin-bottom: 24px;
        }

        .quick-action-item {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 1px 4px rgba(0,0,0,0.1);
            text-decoration: none;
            color: #333;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .quick-action-item:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            color: #1890ff;
        }

        .quick-action-icon {
            width: 48px;
            height: 48px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
            margin-bottom: 12px;
        }

        .quick-action-text {
            font-size: 14px;
            font-weight: 500;
        }

        /* 响应式 */
        @media (max-width: 1200px) {
            .dashboard-cards {
                grid-template-columns: repeat(2, 1fr);
            }
            .quick-actions {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 768px) {
            .dashboard-cards {
                grid-template-columns: 1fr;
            }
            .chart-row {
                grid-template-columns: 1fr;
            }
            .quick-actions {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <!-- 顶部导航 -->
    <div class="header">
        <div class="header-logo">物业管理系统</div>
        <div class="header-right">
            <span class="header-user">欢迎，<%= currentUsername %></span>
            <a href="<%= path %>/logout" class="header-logout">退出</a>
        </div>
    </div>

    <!-- 侧边栏 -->
    <div class="sider">
        <ul class="sider-menu">
            <%-- 主控台：所有角色可见 --%>
            <li><a href="<%= path %>/index" class="<%= "index".equals(activeMenu) ? "active" : "" %>"><span class="icon">🏠</span> 主控台</a></li>
            <%-- 管理员(1)：全部菜单 --%>
            <% if (jobType == 1 || jobType == 0) { %>
            <li><a href="<%= path %>/user/list" class="<%= "user".equals(activeMenu) ? "active" : "" %>"><span class="icon">👤</span> 用户管理</a></li>
            <li><a href="<%= path %>/building/list" class="<%= "building".equals(activeMenu) ? "active" : "" %>"><span class="icon">🏢</span> 楼栋管理</a></li>
            <li><a href="<%= path %>/house/list" class="<%= "house".equals(activeMenu) ? "active" : "" %>"><span class="icon">🏡</span> 房屋管理</a></li>
            <li><a href="<%= path %>/owner/list" class="<%= "owner".equals(activeMenu) ? "active" : "" %>"><span class="icon">👥</span> 业主管理</a></li>
            <li><a href="<%= path %>/fee/list" class="<%= "fee".equals(activeMenu) ? "active" : "" %>"><span class="icon">💰</span> 账单管理</a></li>
            <li><a href="<%= path %>/repair/list" class="<%= "repair".equals(activeMenu) ? "active" : "" %>"><span class="icon">🔧</span> 报修管理</a></li>
            <li><a href="<%= path %>/car/list" class="<%= "car".equals(activeMenu) ? "active" : "" %>"><span class="icon">🚗</span> 访客车辆</a></li>
            <li><a href="<%= path %>/notice/list" class="<%= "notice".equals(activeMenu) ? "active" : "" %>"><span class="icon">📢</span> 公告管理</a></li>
            <% } %>
            <%-- 收费员(2)：账单管理 --%>
            <% if (jobType == 2) { %>
            <li><a href="<%= path %>/fee/list" class="<%= "fee".equals(activeMenu) ? "active" : "" %>"><span class="icon">💰</span> 账单管理</a></li>
            <% } %>
            <%-- 维修师傅(3)：报修管理 --%>
            <% if (jobType == 3) { %>
            <li><a href="<%= path %>/repair/list" class="<%= "repair".equals(activeMenu) ? "active" : "" %>"><span class="icon">🔧</span> 报修管理</a></li>
            <% } %>
            <%-- 保安(4)：访客车辆 --%>
            <% if (jobType == 4) { %>
            <li><a href="<%= path %>/car/list" class="<%= "car".equals(activeMenu) ? "active" : "" %>"><span class="icon">🚗</span> 访客车辆</a></li>
            <% } %>
        </ul>
    </div>

    <!-- 主内容区 -->
    <div class="main">
        <div class="page-header">
            <h2>${param.title}</h2>
        </div>
        <div class="content-card">
