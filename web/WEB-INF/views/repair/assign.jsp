<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="派单" />
</jsp:include>

<div style="max-width:500px;">
    <div class="form-group">
        <label>报修信息</label>
        <p><strong>${repair.buildName} ${repair.houseNo}</strong> - ${repair.ownerName} (${repair.phone})</p>
        <p>类型：${repair.repairType} | 描述：${repair.content}</p>
    </div>
    <form action="${pageContext.request.contextPath}/repair/assign" method="post">
        <input type="hidden" name="id" value="${repair.id}">
        <div class="form-group">
            <label>选择维修师傅</label>
            <select name="workerId" required>
                <option value="">请选择</option>
                <c:forEach items="${workers}" var="w">
                    <option value="${w.id}">${w.realName} (${w.phone})</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">确认派单</button>
        <a href="${pageContext.request.contextPath}/repair/list" class="btn">取消</a>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>
