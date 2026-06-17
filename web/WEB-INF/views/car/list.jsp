<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="访客车辆记录" />
</jsp:include>

<div class="toolbar">
    <span></span>
    <div>
        <a href="${pageContext.request.contextPath}/car/in" class="btn btn-primary">+ 进入登记</a>
        <a href="${pageContext.request.contextPath}/car/out" class="btn">离开登记</a>
    </div>
</div>
<table>
    <thead>
        <tr><th>ID</th><th>访客姓名</th><th>车牌号</th><th>拜访房屋</th><th>进入时间</th><th>离开时间</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="cv">
        <tr>
            <td>${cv.id}</td>
            <td>${cv.visitorName}</td>
            <td>${cv.plateNum}</td>
            <td>${cv.houseId}</td>
            <td>${cv.inTime}</td>
            <td>${cv.outTime}</td>
            <td>
                <a href="${pageContext.request.contextPath}/car/delete?id=${cv.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定删除？')">删除</a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
