<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="${empty building ? '新增楼栋' : '编辑楼栋'}" />
</jsp:include>

<form action="${pageContext.request.contextPath}/building/${empty building ? 'add' : 'edit'}" method="post" style="max-width:500px;">
    <c:if test="${not empty building}">
        <input type="hidden" name="id" value="${building.id}">
    </c:if>
    <div class="form-group">
        <label>楼栋名</label>
        <input type="text" name="buildName" value="${building.buildName}" required placeholder="如：1号楼">
    </div>
    <div class="form-group">
        <label>总楼层</label>
        <input type="number" name="floorCount" value="${building.floorCount}" required min="1">
    </div>
    <button type="submit" class="btn btn-primary">保存</button>
    <a href="${pageContext.request.contextPath}/building/list" class="btn">取消</a>
</form>

<%@ include file="../common/footer.jsp" %>
