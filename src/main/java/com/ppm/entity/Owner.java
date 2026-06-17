package com.ppm.entity;

/**
 * 业主实体类
 */
public class Owner {
    private Long id;
    private Long houseId;
    private String ownerName;
    private String phone;
    private String idCard;
    private Integer liveStatus;
    private Integer isDelete;
    private String createTime;

    // 联表查询用
    private String houseNo;
    private String buildName;

    public Owner() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getHouseId() { return houseId; }
    public void setHouseId(Long houseId) { this.houseId = houseId; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }
    public Integer getLiveStatus() { return liveStatus; }
    public void setLiveStatus(Integer liveStatus) { this.liveStatus = liveStatus; }
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
        return "Owner{id=" + id + ", ownerName='" + ownerName + "', phone='" + phone + "', houseId=" + houseId + "}";
    }
}
