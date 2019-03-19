package com.SpringAppVersion2.result;

// a simple POJO class to store all the required fields to display on the frontend.
public class PoolTableResultObject {

    private String poolName;

    private String poolCondition;

    private int buildingNo;

    private String buildingName;

    private int floorNo;

    private String status;

    public PoolTableResultObject() { }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getPoolCondition() {
        return poolCondition;
    }

    public void setPoolCondition(String poolCondition) {
        this.poolCondition = poolCondition;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

}
