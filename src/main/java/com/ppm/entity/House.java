package com.ppm.entity;

import java.math.BigDecimal;

/**
 * 房屋实体类
 */
public class House {
    private Long id;
    private Long buildId;
    private String houseNo;
    private BigDecimal area;
    private BigDecimal unitPrice;
    private Integer status;
    private Integer isDelete;
    private String createTime;

    // 联表查询用
    private String buildName;

    public House() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBuildId() { return buildId; }
    public void setBuildId(Long buildId) { this.buildId = buildId; }
    public String getHouseNo() { return houseNo; }
    public void setHouseNo(String houseNo) { this.houseNo = houseNo; }
    public BigDecimal getArea() { return area; }
    public void setArea(BigDecimal area) { this.area = area; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getIsDelete() { return isDelete; }
    public void setIsDelete(Integer isDelete) { this.isDelete = isDelete; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public String getBuildName() { return buildName; }
    public void setBuildName(String buildName) { this.buildName = buildName; }

    @Override
    public String toString() {
        return "House{id=" + id + ", houseNo='" + houseNo + "', area=" + area + ", status=" + status + "}";
    }
}
