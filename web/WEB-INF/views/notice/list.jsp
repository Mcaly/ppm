<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="公告管理" />
</jsp:include>

<div class="toolbar">
    <span></span>
    <a href="${pageContext.request.contextPath}/notice/add" class="btn btn-primary">+ 发布公告</a>
</div>
<table>
    <thead>
        <tr><th>ID</th><th>标题</th><th>发布人</th><th>置顶</th><th>发布时间</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="n">
        <tr>
            <td>${n.id}</td>
            <td>
                <c:if test="${n.isTop == 1}"><span class="status-tag status-error" style="margin-right:6px;">置顶</span></c:if>
                ${n.title}
            </td>
            <td>${n.publishUserName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/notice/toggleTop?id=${n.id}&isTop=${n.isTop}" class="btn btn-sm">
                    <c:choose>
                        <c:when test="${n.isTop == 1}">取消置顶</c:when>
                        <c:otherwise>置顶</c:otherwise>
                    </c:choose>
                </a>
            </td>
            <td>${n.createTime}</td>
            <td>
                <a href="${pageContext.request.contextPath}/notice/edit?id=${n.id}" class="btn btn-sm">编辑</a>
                <a href="${pageContext.request.contextPath}/notice/delete?id=${n.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
