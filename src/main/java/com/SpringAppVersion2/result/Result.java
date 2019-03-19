package com.SpringAppVersion2.result;

import com.SpringAppVersion2.result.enumPack.Message;

public class Result {

  private Long resultId;

  private Message message;

  public Long getResultId() {
    return resultId;
  }

  public void setResultId(Long resultId) {
    this.resultId = resultId;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }
}
