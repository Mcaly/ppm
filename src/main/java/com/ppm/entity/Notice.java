package com.ppm.entity;

/**
 * 通知公告实体类
 */
public class Notice {
    private Long id;
    private String title;
    private String content;
    private Long publishUser;
    private Integer isTop;
    private Integer isDelete;
    private String createTime;

    // 联表查询用
    private String publishUserName;

    public Notice() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Long getPublishUser() { return publishUser; }
    public void setPublishUser(Long publishUser) { this.publishUser = publishUser; }
    public Integer getIsTop() { return isTop; }
    public void setIsTop(Integer isTop) { this.isTop = isTop; }
    public Integer getIsDelete() { return isDelete; }
    public void setIsDelete(Integer isDelete) { this.isDelete = isDelete; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public String getPublishUserName() { return publishUserName; }
    public void setPublishUserName(String publishUserName) { this.publishUserName = publishUserName; }

    @Override
    public String toString() {
        return "Notice{id=" + id + ", title='" + title + "', isTop=" + isTop + "}";
    }
}
