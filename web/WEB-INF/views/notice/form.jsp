<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="${empty notice ? '发布公告' : '编辑公告'}" />
</jsp:include>

<form action="${pageContext.request.contextPath}/notice/${empty notice ? 'add' : 'edit'}" method="post" style="max-width:600px;">
    <c:if test="${not empty notice}">
        <input type="hidden" name="id" value="${notice.id}">
    </c:if>
    <div class="form-group">
        <label>标题</label>
        <input type="text" name="title" value="${notice.title}" required>
    </div>
    <div class="form-group">
        <label>内容</label>
        <textarea name="content" rows="8" required style="width:100%;padding:8px 12px;border:1px solid #d9d9d9;border-radius:6px;font-size:14px;">${notice.content}</textarea>
    </div>
    <div class="form-group">
        <label>是否置顶</label>
        <select name="isTop">
            <option value="0" ${notice.isTop == 0 ? 'selected' : ''}>否</option>
            <option value="1" ${notice.isTop == 1 ? 'selected' : ''}>是</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">保存</button>
    <a href="${pageContext.request.contextPath}/notice/list" class="btn">取消</a>
</form>

<%@ include file="../common/footer.jsp" %>
