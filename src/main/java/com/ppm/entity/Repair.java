package com.ppm.entity;

/**
 * 维修工单实体类
 */
public class Repair {
    private Long id;
    private Long houseId;
    private String ownerName;
    private String phone;
    private String repairType;
    private String content;
    private Long workerId;
    private Integer repairStatus;
    private String finishTime;
    private Integer isDelete;
    private String createTime;

    // 联表查询用
    private String houseNo;
    private String buildName;
    private String workerName;

    public Repair() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getHouseId() { return houseId; }
    public void setHouseId(Long houseId) { this.houseId = houseId; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRepairType() { return repairType; }
    public void setRepairType(String repairType) { this.repairType = repairType; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Long getWorkerId() { return workerId; }
    public void setWorkerId(Long workerId) { this.workerId = workerId; }
    public Integer getRepairStatus() { return repairStatus; }
    public void setRepairStatus(Integer repairStatus) { this.repairStatus = repairStatus; }
    public String getFinishTime() { return finishTime; }
    public void setFinishTime(String finishTime) { this.finishTime = finishTime; }
    public Integer getIsDelete() { return isDelete; }
    public void setIsDelete(Integer isDelete) { this.isDelete = isDelete; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
    public String getHouseNo() { return houseNo; }
    public void setHouseNo(String houseNo) { this.houseNo = houseNo; }
    public String getBuildName() { return buildName; }
    public void setBuildName(String buildName) { this.buildName = buildName; }
    public String getWorkerName() { return workerName; }
    public void setWorkerName(String workerName) { this.workerName = workerName; }

    @Override
    public String toString() {
        return "Repair{id=" + id + ", ownerName='" + ownerName + "', repairType='" + repairType + "', repairStatus=" + repairStatus + "}";
    }
}
