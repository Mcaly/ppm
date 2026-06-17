<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="${empty house ? '新增房屋' : '编辑房屋'}" />
</jsp:include>

<form action="${pageContext.request.contextPath}/house/${empty house ? 'add' : 'edit'}" method="post" style="max-width:500px;">
    <c:if test="${not empty house}">
        <input type="hidden" name="id" value="${house.id}">
    </c:if>
    <div class="form-group">
        <label>所属楼栋</label>
        <select name="buildId" required>
            <option value="">请选择楼栋</option>
            <c:forEach items="${buildings}" var="b">
                <option value="${b.id}" ${house.buildId == b.id ? 'selected' : ''}>${b.buildName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label>房号</label>
        <input type="text" name="houseNo" value="${house.houseNo}" required placeholder="如：1-101">
    </div>
    <div class="form-group">
        <label>建筑面积(㎡)</label>
        <input type="number" step="0.01" name="area" value="${house.area}" required>
    </div>
    <div class="form-group">
        <label>物业费单价(元/㎡)</label>
        <input type="number" step="0.01" name="unitPrice" value="${house.unitPrice}" required>
    </div>
    <div class="form-group">
        <label>状态</label>
        <select name="status" required>
            <option value="1" ${house.status == 1 ? 'selected' : ''}>空置</option>
            <option value="2" ${house.status == 2 ? 'selected' : ''}>已入住</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">保存</button>
    <a href="${pageContext.request.contextPath}/house/list" class="btn">取消</a>
</form>

<%@ include file="../common/footer.jsp" %>
