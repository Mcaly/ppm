<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="楼栋管理" />
</jsp:include>

<div class="toolbar">
    <span></span>
    <a href="${pageContext.request.contextPath}/building/add" class="btn btn-primary">+ 新增楼栋</a>
</div>
<table>
    <thead>
        <tr><th>ID</th><th>楼栋名</th><th>总楼层</th><th>创建时间</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.buildName}</td>
            <td>${b.floorCount}</td>
            <td>${b.createTime}</td>
            <td>
                <a href="${pageContext.request.contextPath}/building/edit?id=${b.id}" class="btn btn-sm">编辑</a>
                <a href="${pageContext.request.contextPath}/building/delete?id=${b.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
