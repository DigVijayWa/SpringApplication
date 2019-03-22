package com.SpringAppVersion2.result;

public class UserResultObject {

  private String userName;

  private Long userId;

  private String userEmail;

  private int totalBookingsPossible;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public int getTotalBookingsPossible() {
    return totalBookingsPossible;
  }

  public void setTotalBookingsPossible(int totalBookingsPossible) {
    this.totalBookingsPossible = totalBookingsPossible;
  }
}
