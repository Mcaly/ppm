<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="生成账单" />
</jsp:include>

<form action="${pageContext.request.contextPath}/fee/generate" method="post" style="max-width:600px;">
    <div class="form-group">
        <label>账单月份</label>
        <input type="month" name="billMonth" required>
    </div>
    <div class="form-group">
        <label>选择房屋（勾选要生成账单的房屋）</label>
        <table>
            <thead>
                <tr><th><input type="checkbox" id="checkAll"></th><th>楼栋</th><th>房号</th><th>面积(㎡)</th><th>单价(元/㎡)</th><th>预计金额</th></tr>
            </thead>
            <tbody>
                <c:forEach items="${houses}" var="h">
                <tr>
                    <td><input type="checkbox" name="houseIds" value="${h.id}" class="house-cb"></td>
                    <td>${h.buildName}</td>
                    <td>${h.houseNo}</td>
                    <td>${h.area}</td>
                    <td>${h.unitPrice}</td>
                    <td>${h.area * h.unitPrice}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <button type="submit" class="btn btn-primary">确认生成</button>
    <a href="${pageContext.request.contextPath}/fee/list" class="btn">取消</a>
</form>

<script>
    document.getElementById('checkAll').addEventListener('change', function() {
        document.querySelectorAll('.house-cb').forEach(cb => cb.checked = this.checked);
    });
</script>

<%@ include file="../common/footer.jsp" %>
