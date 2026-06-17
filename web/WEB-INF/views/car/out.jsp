<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="离开登记" />
</jsp:include>

<table>
    <thead>
        <tr><th>ID</th><th>访客姓名</th><th>车牌号</th><th>进入时间</th><th>操作</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="cv">
        <tr>
            <td>${cv.id}</td>
            <td>${cv.visitorName}</td>
            <td>${cv.plateNum}</td>
            <td>${cv.inTime}</td>
            <td>
                <a href="${pageContext.request.contextPath}/car/doOut?id=${cv.id}" class="btn btn-sm btn-primary" onclick="return confirm('确认离开？')">登记离开</a>
            </td>
        </tr>
        </c:forEach>
        <c:if test="${empty list}">
            <tr><td colspan="5" style="text-align:center;color:#999;">当前无在场访客</td></tr>
        </c:if>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %>
