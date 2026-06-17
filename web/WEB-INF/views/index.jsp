<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
    <jsp:param name="title" value="主控台" />
</jsp:include>

<!-- 统计卡片 -->
<div class="dashboard-cards">
    <div class="stat-card">
        <div class="stat-icon stat-icon-blue">🏠</div>
        <div class="stat-info">
            <div class="stat-title">房屋总数</div>
            <div class="stat-value">
                <c:choose>
                    <c:when test="${loginUser.jobType == 1}">${houseCount}</c:when>
                    <c:otherwise>*</c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="stat-card">
        <div class="stat-icon stat-icon-orange">💰</div>
        <div class="stat-info">
            <div class="stat-title">未缴账单</div>
            <div class="stat-value">
                <c:choose>
                    <c:when test="${loginUser.jobType == 1 || loginUser.jobType == 2}">${unpaidCount}</c:when>
                    <c:otherwise>*</c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="stat-card">
        <div class="stat-icon stat-icon-red">🔧</div>
        <div class="stat-info">
            <div class="stat-title">待派工单</div>
            <div class="stat-value">
                <c:choose>
                    <c:when test="${loginUser.jobType == 1 || loginUser.jobType == 3}">${pendingRepair}</c:when>
                    <c:otherwise>*</c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="stat-card">
        <div class="stat-icon stat-icon-green">🚗</div>
        <div class="stat-info">
            <div class="stat-title">今日访客</div>
            <div class="stat-value">
                <c:choose>
                    <c:when test="${loginUser.jobType == 1 || loginUser.jobType == 4}">${todayVisitor}</c:when>
                    <c:otherwise>*</c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<!-- 下方两列 -->
<div class="chart-row">
    <!-- 最新公告 -->
    <div class="list-card">
        <div class="list-card-header">
            <span class="list-card-title">📢 最新公告</span>
            <a href="${pageContext.request.contextPath}/notice/list" class="btn btn-sm">查看全部</a>
        </div>
        <div class="list-card-body">
            <c:forEach items="${notices}" var="n" end="4">
                <div class="list-item" style="cursor:pointer"
                     data-title="${fn:escapeXml(n.title)}"
                     data-content="${fn:escapeXml(n.content)}"
                     data-user="${fn:escapeXml(n.publishUserName)}"
                     data-time="${fn:escapeXml(n.createTime)}"
                     onclick="showNotice(this)">
                    <div class="list-item-content">
                        <div class="list-item-title">
                            <c:if test="${n.isTop == 1}"><span class="status-tag status-error" style="margin-right:6px;">置顶</span></c:if>
                            ${n.title}
                        </div>
                        <div class="list-item-desc">
                            <c:choose>
                                <c:when test="${fn:length(n.content) > 50}">${fn:substring(n.content, 0, 50)}...</c:when>
                                <c:otherwise>${n.content}</c:otherwise>
                            </c:choose>
                        </div>
                        <div class="list-item-desc" style="margin-top:4px;color:#bbb;">${n.publishUserName}</div>
                    </div>
                    <span class="list-item-time">${n.createTime}</span>
                </div>
            </c:forEach>
            <c:if test="${empty notices}">
                <div class="list-item"><div class="list-item-content"><div class="list-item-desc">暂无公告</div></div></div>
            </c:if>
        </div>
    </div>

    <!-- 最新报修 -->
    <c:if test="${loginUser.jobType == 1 || loginUser.jobType == 3}">
        <div class="list-card">
            <div class="list-card-header">
                <span class="list-card-title">🔧 最新报修</span>
                <a href="${pageContext.request.contextPath}/repair/list" class="btn btn-sm">查看全部</a>
            </div>
            <div class="list-card-body">
                <c:forEach items="${repairs}" var="r" end="4">
                    <div class="list-item">
                        <div class="list-item-content">
                            <div class="list-item-title">${r.ownerName} - ${r.repairType}</div>
                            <div class="list-item-desc">
                                <c:choose>
                                    <c:when test="${r.repairStatus == 0}"><span class="status-tag status-warning">待派单</span></c:when>
                                    <c:when test="${r.repairStatus == 1}"><span class="status-tag status-info">维修中</span></c:when>
                                    <c:when test="${r.repairStatus == 2}"><span class="status-tag status-success">已完成</span></c:when>
                                </c:choose>
                            </div>
                        </div>
                        <span class="list-item-time">${r.createTime}</span>
                    </div>
                </c:forEach>
                <c:if test="${empty repairs}">
                    <div class="list-item"><div class="list-item-content"><div class="list-item-desc">暂无报修</div></div></div>
                </c:if>
            </div>
        </div>
    </c:if>

</div>

<!-- 公告详情弹窗 -->
<div id="noticeModal" style="display:none;position:fixed;top:0;left:0;right:0;bottom:0;background:rgba(0,0,0,0.5);z-index:2000;justify-content:center;align-items:center;" onclick="if(event.target===this)closeNoticeModal()">
    <div style="background:#fff;border-radius:8px;width:560px;max-height:80vh;overflow:auto;box-shadow:0 4px 20px rgba(0,0,0,0.2);">
        <div style="padding:20px 24px;border-bottom:1px solid #f0f0f0;display:flex;justify-content:space-between;align-items:center;">
            <h3 id="modalTitle" style="margin:0;font-size:16px;color:#333;"></h3>
            <span style="cursor:pointer;font-size:20px;color:#999;line-height:1;" onclick="closeNoticeModal()">&times;</span>
        </div>
        <div style="padding:20px 24px;">
            <div id="modalContent" style="font-size:14px;color:#333;line-height:1.8;white-space:pre-wrap;"></div>
            <div style="margin-top:16px;font-size:12px;color:#999;">
                <span id="modalUser"></span> · <span id="modalTime"></span>
            </div>
        </div>
    </div>
</div>

<script>
function showNotice(el) {
    document.getElementById('modalTitle').textContent = el.dataset.title;
    document.getElementById('modalContent').textContent = el.dataset.content;
    document.getElementById('modalUser').textContent = el.dataset.user;
    document.getElementById('modalTime').textContent = el.dataset.time;
    document.getElementById('noticeModal').style.display = 'flex';
}
function closeNoticeModal() {
    document.getElementById('noticeModal').style.display = 'none';
}
document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') closeNoticeModal();
});
</script>

<%@ include file="common/footer.jsp" %>
