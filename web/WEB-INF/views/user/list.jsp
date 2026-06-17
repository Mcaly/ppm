<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="用户管理" />
</jsp:include>

<div class="toolbar">
    <span></span>
    <a href="${pageContext.request.contextPath}/user/add" class="btn btn-primary">+ 新增用户</a>
</div>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>账号</th>
            <th>姓名</th>
            <th>手机号</th>
            <th>角色</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.userName}</td>
            <td>${u.realName}</td>
            <td>${u.phone}</td>
            <td>
                <c:choose>
                    <c:when test="${u.jobType == 1}"><span class="status-tag status-info">管理员</span></c:when>
                    <c:when test="${u.jobType == 2}"><span class="status-tag status-success">收费员</span></c:when>
                    <c:when test="${u.jobType == 3}"><span class="status-tag status-warning">维修师傅</span></c:when>
                    <c:when test="${u.jobType == 4}"><span class="status-tag status-error">保安</span></c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${u.status == 1}"><span class="status-tag status-success">正常</span></c:when>
                    <c:otherwise><span class="status-tag status-error">停用</span></c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/user/edit?id=${u.id}" class="btn btn-sm">编辑</a>
                <a href="${pageContext.request.contextPath}/user/delete?id=${u.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
