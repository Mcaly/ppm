<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="房屋管理" />
</jsp:include>

<div class="toolbar">
    <span></span>
    <a href="${pageContext.request.contextPath}/house/add" class="btn btn-primary">+ 新增房屋</a>
</div>
<table>
    <thead>
        <tr><th>ID</th><th>楼栋</th><th>房号</th><th>面积(㎡)</th><th>物业费单价</th><th>状态</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="h">
        <tr>
            <td>${h.id}</td>
            <td>${h.buildName}</td>
            <td>${h.houseNo}</td>
            <td>${h.area}</td>
            <td>${h.unitPrice}</td>
            <td>
                <c:choose>
                    <c:when test="${h.status == 1}"><span class="status-tag status-warning">空置</span></c:when>
                    <c:when test="${h.status == 2}"><span class="status-tag status-success">已入住</span></c:when>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/house/edit?id=${h.id}" class="btn btn-sm">编辑</a>
                <a href="${pageContext.request.contextPath}/house/delete?id=${h.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
