<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="新增报修" />
</jsp:include>

<form action="${pageContext.request.contextPath}/repair/add" method="post" style="max-width:500px;">
    <div class="form-group">
        <label>报修房屋</label>
        <select name="houseId" required>
            <option value="">请选择房屋</option>
            <c:forEach items="${houses}" var="h">
                <option value="${h.id}">${h.buildName} ${h.houseNo}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label>报修人</label>
        <input type="text" name="ownerName" required>
    </div>
    <div class="form-group">
        <label>联系电话</label>
        <input type="text" name="phone" required>
    </div>
    <div class="form-group">
        <label>维修类型</label>
        <select name="repairType" required>
            <option value="水电">水电</option>
            <option value="门窗">门窗</option>
            <option value="公共设施">公共设施</option>
            <option value="其他">其他</option>
        </select>
    </div>
    <div class="form-group">
        <label>故障描述</label>
        <textarea name="content" rows="4" style="width:100%;padding:8px 12px;border:1px solid #d9d9d9;border-radius:6px;"></textarea>
    </div>
    <button type="submit" class="btn btn-primary">提交</button>
    <a href="${pageContext.request.contextPath}/repair/list" class="btn">取消</a>
</form>

<%@ include file="../common/footer.jsp" %>
