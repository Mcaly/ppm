<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="${empty owner ? '新增业主' : '编辑业主'}" />
</jsp:include>

<form action="${pageContext.request.contextPath}/owner/${empty owner ? 'add' : 'edit'}" method="post" style="max-width:500px;">
    <c:if test="${not empty owner}">
        <input type="hidden" name="id" value="${owner.id}">
    </c:if>
    <div class="form-group">
        <label>绑定房屋</label>
        <select name="houseId" required>
            <option value="">请选择房屋</option>
            <c:forEach items="${houses}" var="h">
                <option value="${h.id}" ${owner.houseId == h.id ? 'selected' : ''}>${h.buildName} ${h.houseNo}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label>业主姓名</label>
        <input type="text" name="ownerName" value="${owner.ownerName}" required>
    </div>
    <div class="form-group">
        <label>联系电话</label>
        <input type="text" name="phone" value="${owner.phone}" required>
    </div>
    <div class="form-group">
        <label>身份证号</label>
        <input type="text" name="idCard" value="${owner.idCard}">
    </div>
    <div class="form-group">
        <label>居住状态</label>
        <select name="liveStatus" required>
            <option value="1" ${owner.liveStatus == 1 ? 'selected' : ''}>自住</option>
            <option value="2" ${owner.liveStatus == 2 ? 'selected' : ''}>出租</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">保存</button>
    <a href="${pageContext.request.contextPath}/owner/list" class="btn">取消</a>
</form>

<%@ include file="../common/footer.jsp" %>
