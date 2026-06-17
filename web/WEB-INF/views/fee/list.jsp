<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="账单管理" />
</jsp:include>

<div class="toolbar">
    <form class="search-bar" action="${pageContext.request.contextPath}/fee/list" method="get">
        <select name="houseId">
            <option value="">全部房屋</option>
            <c:forEach items="${houses}" var="h">
                <option value="${h.id}" ${paramHouseId == h.id.toString() ? 'selected' : ''}>${h.buildName} ${h.houseNo}</option>
            </c:forEach>
        </select>
        <input type="month" name="billMonth" value="${paramBillMonth}">
        <select name="payStatus">
            <option value="">全部状态</option>
            <option value="0" ${paramPayStatus == '0' ? 'selected' : ''}>未缴</option>
            <option value="1" ${paramPayStatus == '1' ? 'selected' : ''}>已缴</option>
        </select>
        <button type="submit" class="btn">筛选</button>
    </form>
    <a href="${pageContext.request.contextPath}/fee/generate" class="btn btn-primary">+ 生成账单</a>
</div>
<table>
    <thead>
        <tr><th>ID</th><th>楼栋</th><th>房号</th><th>账单月份</th><th>金额(元)</th><th>状态</th><th>缴费时间</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="f">
        <tr>
            <td>${f.id}</td>
            <td>${f.buildName}</td>
            <td>${f.houseNo}</td>
            <td>${f.billMonth}</td>
            <td>${f.totalFee}</td>
            <td>
                <c:choose>
                    <c:when test="${f.payStatus == 0}"><span class="status-tag status-warning">未缴</span></c:when>
                    <c:when test="${f.payStatus == 1}"><span class="status-tag status-success">已缴</span></c:when>
                </c:choose>
            </td>
            <td>${f.payTime}</td>
            <td>
                <c:if test="${f.payStatus == 0}">
                    <a href="${pageContext.request.contextPath}/fee/pay?id=${f.id}" class="btn btn-sm btn-primary" onclick="return confirm('确认标记为已缴？')">标记已缴</a>
                </c:if>
                <a href="${pageContext.request.contextPath}/fee/delete?id=${f.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
