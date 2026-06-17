package com.ppm.entity;

import java.math.BigDecimal;

/**
 * 费用账单实体类
 */
public class FeeBill {
    private Long id;
    private Long houseId;
    private String billMonth;
    private BigDecimal totalFee;
    private Integer payStatus;
    private String payTime;
    private Long operUserId;
    private Integer isDelete;
    private String createTime;

    // 联表查询用
    private String houseNo;
    private String buildName;

    public FeeBill() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getHouseId() { return houseId; }
    public void setHouseId(Long houseId) { this.houseId = houseId; }
    public String getBillMonth() { return billMonth; }
    public void setBillMonth(String billMonth) { this.billMonth = billMonth; }
    public BigDecimal getTotalFee() { return totalFee; }
    public void setTotalFee(BigDecimal totalFee) { this.totalFee = totalFee; }
    public Integer getPayStatus() { return payStatus; }
    public void setPayStatus(Integer payStatus) { this.payStatus = payStatus; }
    public String getPayTime() { return payTime; }
    public void setPayTime(String payTime) { this.payTime = payTime; }
    public Long getOperUserId() { return operUserId; }
    public void setOperUserId(Long operUserId) { this.operUserId = operUserId; }
    public Integer getIsDelete() { return isDelete; }
    public void setIsDelete(Integer isDelete) { this.isDelete = isDelete; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public String getHouseNo() { return houseNo; }
    public void setHouseNo(String houseNo) { this.houseNo = houseNo; }
    public String getBuildName() { return buildName; }
    public void setBuildName(String buildName) { this.buildName = buildName; }

    @Override
    public String toString() {
        return "FeeBill{id=" + id + ", houseId=" + houseId + ", billMonth='" + billMonth + "', totalFee=" + totalFee + ", payStatus=" + payStatus + "}";
    }
}
