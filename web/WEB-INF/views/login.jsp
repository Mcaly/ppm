<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录 - 物业管理系统</title>
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
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-box {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
            width: 400px;
            overflow: hidden;
        }

        .login-header {
            background: #1890ff;
            padding: 30px;
            text-align: center;
            color: white;
        }

        .login-header h1 {
            font-size: 24px;
            margin-bottom: 5px;
        }

        .login-header p {
            font-size: 14px;
            opacity: 0.9;
        }

        .login-body {
            padding: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
            color: #333;
            font-weight: 500;
        }

        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #d9d9d9;
            border-radius: 6px;
            font-size: 14px;
            outline: none;
            transition: border-color 0.3s;
        }

        .form-group input[type="text"]:focus,
        .form-group input[type="password"]:focus {
            border-color: #1890ff;
            box-shadow: 0 0 0 2px rgba(24, 144, 255, 0.2);
        }

        .error-msg {
            background: #fff2f0;
            border: 1px solid #ffccc7;
            color: #ff4d4f;
            padding: 10px 12px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 13px;
        }

        .login-btn {
            width: 100%;
            padding: 10px;
            background: #1890ff;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: background 0.3s;
        }

        .login-btn:hover {
            background: #40a9ff;
        }

        .login-btn:active {
            background: #096dd9;
        }

        .form-footer {
            text-align: center;
            margin-top: 20px;
            font-size: 13px;
            color: #666;
        }

        .form-footer a {
            color: #1890ff;
            text-decoration: none;
        }

        .form-footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%
    String error = request.getParameter("error");
    String errorMessage = null;
    if ("invalid".equals(error)) {
        errorMessage = "用户名或密码错误";
    } else if ("empty".equals(error)) {
        errorMessage = "用户名和密码不能为空";
    } else if ("needLogin".equals(error)) {
        errorMessage = "请先登录";
    }

    String username = "";
%>
<div class="login-box">
    <div class="login-header">
        <h1>物业管理系统</h1>
        <p>请登录您的账户</p>
    </div>
    <div class="login-body">
        <% if (errorMessage != null) { %>
        <div class="error-msg"><%= errorMessage %></div>
        <% } %>

        <form action="<%= request.getContextPath() %>/login" method="post">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" id="username" name="username" value="<%= username %>" placeholder="请输入用户名" required>
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" id="password" name="password" placeholder="请输入密码" required>
            </div>
            <button type="submit" class="login-btn">登 录</button>
        </form>
        <div class="form-footer">
            <a href="<%= request.getContextPath() %>/query">业主自助查询</a>
            &nbsp;|&nbsp;
            <a href="#">忘记密码？</a>
        </div>
    </div>
</div>
<script>
    document.getElementById('username').focus();
</script>
</body>
</html>
