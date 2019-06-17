package com.SpringAppVersion2.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

public class UserMatchKey implements Serializable {

  private static final long serialVersionUID = 8716563105276174721L;

  @Column(name="user_id")
  private Long userId;

  @Column(name="match_id")
  private Long matchId;

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getMatchId() {
    return matchId;
  }

  public void setMatchId(Long matchId) {
    this.matchId = matchId;
  }
}
