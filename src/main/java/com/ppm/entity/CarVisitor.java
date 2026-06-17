package com.ppm.entity;

/**
 * 车辆/访客实体类
 */
public class CarVisitor {
    private Long id;
    private Long houseId;
    private String visitorName;
    private String plateNum;
    private String inTime;
    private String outTime;
    private Long operUserId;
    private Integer isDelete;
    private String createTime;

    // 联表查询用
    private String houseNo;

    public CarVisitor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getHouseId() { return houseId; }
    public void setHouseId(Long houseId) { this.houseId = houseId; }
    public String getVisitorName() { return visitorName; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }
    public String getPlateNum() { return plateNum; }
    public void setPlateNum(String plateNum) { this.plateNum = plateNum; }
    public String getInTime() { return inTime; }
    public void setInTime(String inTime) { this.inTime = inTime; }
    public String getOutTime() { return outTime; }
    public void setOutTime(String outTime) { this.outTime = outTime; }
    public Long getOperUserId() { return operUserId; }
    public void setOperUserId(Long operUserId) { this.operUserId = operUserId; }
    public Integer getIsDelete() { return isDelete; }
    public void setIsDelete(Integer isDelete) { this.isDelete = isDelete; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public String getHouseNo() { return houseNo; }
    public void setHouseNo(String houseNo) { this.houseNo = houseNo; }

    @Override
    public String toString() {
        return "CarVisitor{id=" + id + ", visitorName='" + visitorName + "', plateNum='" + plateNum + "'}";
    }
}
