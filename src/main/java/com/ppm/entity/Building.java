package com.ppm.entity;

/**
 * 楼栋实体类
 */
public class Building {
    private Long id;
    private String buildName;
    private Integer floorCount;
    private Integer isDelete;
    private String createTime;

    public Building() {}

    public Building(Long id, String buildName, Integer floorCount, Integer isDelete, String createTime) {
        this.id = id;
        this.buildName = buildName;
        this.floorCount = floorCount;
        this.isDelete = isDelete;
        this.createTime = createTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBuildName() { return buildName; }
    public void setBuildName(String buildName) { this.buildName = buildName; }
    public Integer getFloorCount() { return floorCount; }
    public void setFloorCount(Integer floorCount) { this.floorCount = floorCount; }
    public Integer getIsDelete() { return isDelete; }
    public void setIsDelete(Integer isDelete) { this.isDelete = isDelete; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "Building{id=" + id + ", buildName='" + buildName + "', floorCount=" + floorCount + "}";
    }
}
