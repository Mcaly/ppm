<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="${empty user ? '新增用户' : '编辑用户'}" />
</jsp:include>

<form action="${pageContext.request.contextPath}/user/${empty user ? 'add' : 'edit'}" method="post" style="max-width:500px;">
    <c:if test="${not empty user}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <div class="form-group">
        <label>账号</label>
        <input type="text" name="userName" value="${user.userName}" required>
    </div>
    <div class="form-group">
        <label>密码 <c:if test="${not empty user}"><small>(留空则不修改)</small></c:if></label>
        <input type="password" name="password" <c:if test="${empty user}">required</c:if>>
    </div>
    <div class="form-group">
        <label>姓名</label>
        <input type="text" name="realName" value="${user.realName}" required>
    </div>
    <div class="form-group">
        <label>手机号</label>
        <input type="text" name="phone" value="${user.phone}">
    </div>
    <div class="form-group">
        <label>角色</label>
        <select name="jobType" required>
            <option value="1" ${user.jobType == 1 ? 'selected' : ''}>管理员</option>
            <option value="2" ${user.jobType == 2 ? 'selected' : ''}>收费员</option>
            <option value="3" ${user.jobType == 3 ? 'selected' : ''}>维修师傅</option>
            <option value="4" ${user.jobType == 4 ? 'selected' : ''}>保安</option>
        </select>
    </div>
    <div class="form-group">
        <label>状态</label>
        <select name="status" required>
            <option value="1" ${user.status == 1 ? 'selected' : ''}>正常</option>
            <option value="0" ${user.status == 0 ? 'selected' : ''}>停用</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">保存</button>
    <a href="${pageContext.request.contextPath}/user/list" class="btn">取消</a>
</form>

<%@ include file="../common/footer.jsp" %>
