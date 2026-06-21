<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="进入登记" />
</jsp:include>

<form action="${pageContext.request.contextPath}/car/in" method="post" style="max-width:500px;">
    <div class="form-group">
        <label>访客姓名</label>
        <input type="text" name="visitorName" required>
    </div>
    <div class="form-group">
        <label>车牌号</label>
        <input type="text" name="plateNum" required placeholder="如：京A12345">
    </div>
    <div class="form-group">
        <label>拜访房屋（可选）</label>
        <select name="houseId">
            <option value="">不指定</option>
            <c:forEach items="${houses}" var="h">
                <option value="${h.id}">${h.buildName} ${h.houseNo}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">确认登记</button>
    <a href="${pageContext.request.contextPath}/car/list" class="btn">取消</a>
</form>

<%@ include file="../common/footer.jsp" %>
