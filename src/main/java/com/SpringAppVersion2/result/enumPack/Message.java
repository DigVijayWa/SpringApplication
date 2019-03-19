package com.SpringAppVersion2.result.enumPack;

public enum Message implements ResultStatus{

  UPDATION_SUCCESS(200,"TABLE UPDATED SUCCESSFULLY"),
  DELETION_SUCCESS(200,"TABLE DELETED SUCCESSFULLY"),
  CREATE_SUCCESS(200,"RECORD CREATED SUCCESSFULLY"),
  UPDATION_FAILURE(302,"TABLE UPDATION FAILED"),
  CREATE_FAILURE(302,"TABLE CREATION FAILED");


  int statusCode;
  String statusMessage;



  Message(int statusCode, String statusMessage) {
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

}
