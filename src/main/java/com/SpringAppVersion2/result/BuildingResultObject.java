package com.SpringAppVersion2.result;

public class BuildingResultObject {

  private Long buildingId;

  private String buildingName;

  private int buildingNo;

  private int floorCount;

  public BuildingResultObject() {

  }

  public Long getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(Long buildingId) {
    this.buildingId = buildingId;
  }

  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  public int getBuildingNo() {
    return buildingNo;
  }

  public void setBuildingNo(int buildingNo) {
    this.buildingNo = buildingNo;
  }

  public int getFloorCount() {
    return floorCount;
  }

  public void setFloorCount(int floorCount) {
    this.floorCount = floorCount;
  }
}
