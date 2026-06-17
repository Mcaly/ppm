<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>业主自助查询 - 物业管理系统</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Microsoft YaHei', sans-serif; background: #f0f2f5; min-height: 100vh; }
        .top-bar { background: #001529; color: #fff; padding: 16px 24px; display: flex; justify-content: space-between; align-items: center; }
        .top-bar h1 { font-size: 18px; }
        .top-bar a { color: rgba(255,255,255,0.65); text-decoration: none; font-size: 14px; }
        .top-bar a:hover { color: #fff; }
        .container { max-width: 900px; margin: 24px auto; padding: 0 16px; }
        .card { background: #fff; border-radius: 8px; box-shadow: 0 1px 4px rgba(0,0,0,0.1); padding: 24px; margin-bottom: 20px; }
        .card-title { font-size: 16px; font-weight: 500; color: #333; margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid #f0f0f0; }
        .form-group { margin-bottom: 16px; }
        .form-group label { display: block; margin-bottom: 6px; font-size: 14px; color: #333; font-weight: 500; }
        .form-group input, .form-group select, .form-group textarea {
            width: 100%; padding: 8px 12px; border: 1px solid #d9d9d9; border-radius: 6px;
            font-size: 14px; outline: none; transition: border-color 0.3s;
        }
        .form-group input:focus, .form-group select:focus, .form-group textarea:focus {
            border-color: #1890ff; box-shadow: 0 0 0 2px rgba(24,144,255,0.2);
        }
        .form-row { display: flex; gap: 12px; align-items: flex-end; }
        .form-row .form-group { flex: 1; margin-bottom: 0; }
        .btn {
            display: inline-block; padding: 8px 20px; border-radius: 6px; font-size: 14px;
            cursor: pointer; text-decoration: none; border: 1px solid #d9d9d9; background: #fff;
            color: #333; transition: all 0.3s;
        }
        .btn:hover { border-color: #1890ff; color: #1890ff; }
        .btn-primary { background: #1890ff; border-color: #1890ff; color: #fff; }
        .btn-primary:hover { background: #40a9ff; border-color: #40a9ff; color: #fff; }
        .error-msg { background: #fff2f0; border: 1px solid #ffccc7; color: #ff4d4f; padding: 10px 12px; border-radius: 6px; margin-bottom: 16px; font-size: 13px; }
        .success-msg { background: #f6ffed; border: 1px solid #b7eb8f; color: #52c41a; padding: 10px 12px; border-radius: 6px; margin-bottom: 16px; font-size: 13px; }
        .info-row { display: flex; gap: 24px; flex-wrap: wrap; margin-bottom: 12px; }
        .info-item { font-size: 14px; color: #333; }
        .info-item span { color: #999; }
        table { width: 100%; border-collapse: collapse; }
        table th, table td { padding: 10px 12px; text-align: left; border-bottom: 1px solid #f0f0f0; font-size: 13px; }
        table th { background: #fafafa; font-weight: 500; color: #333; }
        table tbody tr:hover { background: #f5f5f5; }
        .status-tag { display: inline-block; padding: 2px 8px; border-radius: 4px; font-size: 12px; }
        .status-success { background: #f6ffed; border: 1px solid #b7eb8f; color: #52c41a; }
        .status-warning { background: #fffbe6; border: 1px solid #ffe58f; color: #faad14; }
        .status-info { background: #e6f7ff; border: 1px solid #91d5ff; color: #1890ff; }
        .empty-hint { text-align: center; color: #999; padding: 20px; font-size: 13px; }
        .section-title { font-size: 14px; font-weight: 500; color: #333; margin: 16px 0 10px; }
    </style>
</head>
<body>
<div class="top-bar">
    <h1>🏠 物业管理系统 — 业主自助查询</h1>
    <a href="<%= request.getContextPath() %>/login">员工登录</a>
</div>
<div class="container">
    <%-- 错误/成功提示 --%>
    <c:if test="${not empty error}">
        <div class="error-msg">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="success-msg">${success}</div>
    </c:if>

    <%-- 身份验证卡片 --%>
    <div class="card">
        <div class="card-title">身份验证</div>
        <c:choose>
            <c:when test="${verified}">
                <div class="info-row">
                    <div class="info-item"><span>姓名：</span>${owner.ownerName}</div>
                    <div class="info-item"><span>楼栋：</span>${owner.buildName}</div>
                    <div class="info-item"><span>房号：</span>${owner.houseNo}</div>
                    <div class="info-item"><span>手机号：</span>${owner.phone}</div>
                </div>
            </c:when>
            <c:otherwise>
                <form action="<%= request.getContextPath() %>/query" method="post">
                    <input type="hidden" name="action" value="verify">
                    <div class="form-row">
                        <div class="form-group">
                            <label>手机号</label>
                            <input type="text" name="phone" placeholder="请输入登记的手机号" required>
                        </div>
                        <div class="form-group">
                            <label>身份证号</label>
                            <input type="text" name="idCard" placeholder="请输入身份证号" required>
                        </div>
                        <div class="form-group" style="flex:0">
                            <button type="submit" class="btn btn-primary">验 证</button>
                        </div>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <c:if test="${verified}">
        <%-- 缴费账单 --%>
        <div class="card">
            <div class="card-title">💰 缴费账单</div>
            <c:choose>
                <c:when test="${not empty bills}">
                    <table>
                        <thead>
                            <tr><th>账单月份</th><th>金额(元)</th><th>状态</th><th>缴费时间</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${bills}" var="b">
                            <tr>
                                <td>${b.billMonth}</td>
                                <td>${b.totalFee}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${b.payStatus == 0}"><span class="status-tag status-warning">未缴</span></c:when>
                                        <c:when test="${b.payStatus == 1}"><span class="status-tag status-success">已缴</span></c:when>
                                    </c:choose>
                                </td>
                                <td>${b.payTime}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="empty-hint">暂无账单记录</div>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- 报修记录 --%>
        <div class="card">
            <div class="card-title">🔧 报修记录</div>
            <c:choose>
                <c:when test="${not empty repairs}">
                    <table>
                        <thead>
                            <tr><th>报修时间</th><th>类型</th><th>描述</th><th>维修师傅</th><th>状态</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${repairs}" var="r">
                            <tr>
                                <td>${r.createTime}</td>
                                <td>${r.repairType}</td>
                                <td>${r.content}</td>
                                <td>${r.workerName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${r.repairStatus == 0}"><span class="status-tag status-warning">待派单</span></c:when>
                                        <c:when test="${r.repairStatus == 1}"><span class="status-tag status-info">维修中</span></c:when>
                                        <c:when test="${r.repairStatus == 2}"><span class="status-tag status-success">已完成</span></c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="empty-hint">暂无报修记录</div>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- 在线报修 --%>
        <div class="card">
            <div class="card-title">📝 在线报修</div>
            <form action="<%= request.getContextPath() %>/query" method="post">
                <input type="hidden" name="action" value="repair">
                <input type="hidden" name="phone" value="${owner.phone}">
                <input type="hidden" name="idCard" value="${owner.idCard}">
                <div class="form-group">
                    <label>维修类型</label>
                    <select name="repairType" required>
                        <option value="水电">水电</option>
                        <option value="门窗">门窗</option>
                        <option value="公共设施">公共设施</option>
                        <option value="其他">其他</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>故障描述</label>
                    <textarea name="content" rows="4" placeholder="请详细描述问题" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">提交报修</button>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
