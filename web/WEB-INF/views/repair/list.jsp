<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="报修管理" />
</jsp:include>

<div class="toolbar">
    <span></span>
    <a href="${pageContext.request.contextPath}/repair/add" class="btn btn-primary">+ 新增报修</a>
</div>
<table>
    <thead>
        <tr><th>ID</th><th>楼栋</th><th>房号</th><th>报修人</th><th>电话</th><th>类型</th><th>描述</th><th>维修师傅</th><th>状态</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="r">
        <tr>
            <td>${r.id}</td>
            <td>${r.buildName}</td>
            <td>${r.houseNo}</td>
            <td>${r.ownerName}</td>
            <td>${r.phone}</td>
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
            <td>
                <c:if test="${r.repairStatus == 0}">
                    <a href="${pageContext.request.contextPath}/repair/assign?id=${r.id}" class="btn btn-sm btn-primary">派单</a>
                </c:if>
                <c:if test="${r.repairStatus == 1}">
                    <a href="${pageContext.request.contextPath}/repair/complete?id=${r.id}" class="btn btn-sm btn-primary" onclick="return confirm('确认完工？')">完工</a>
                </c:if>
                <a href="${pageContext.request.contextPath}/repair/delete?id=${r.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
