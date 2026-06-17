
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="业主管理" />
</jsp:include>

<div class="toolbar">
    <span></span>
    <a href="${pageContext.request.contextPath}/owner/add" class="btn btn-primary">+ 新增业主</a>
</div>
<table>
    <thead>
        <tr><th>ID</th><th>业主姓名</th><th>电话</th><th>身份证</th><th>楼栋</th><th>房号</th><th>居住状态</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="o">
        <tr>
            <td>${o.id}</td>
            <td>${o.ownerName}</td>
            <td>${o.phone}</td>
            <td>${o.idCard}</td>
            <td>${o.buildName}</td>
            <td>${o.houseNo}</td>
            <td>
                <c:choose>
                    <c:when test="${o.liveStatus == 1}"><span class="status-tag status-success">自住</span></c:when>
                    <c:when test="${o.liveStatus == 2}"><span class="status-tag status-info">出租</span></c:when>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/owner/edit?id=${o.id}" class="btn btn-sm">编辑</a>
                <a href="${pageContext.request.contextPath}/owner/delete?id=${o.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
