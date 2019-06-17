package com.SpringAppVersion2.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="sys_user_match")
public class UserMatch implements Serializable {

  private static final long serialVersionUID = -2821372040758185403L;

  @EmbeddedId
  UserMatchKey id;

  @ManyToOne
  @MapsId("user_id")
  @JoinColumn(name="user_id")
  User user;

  @ManyToOne
  @MapsId("match_id")
  @JoinColumn(name="match_id")
  Match match;

  @Column(name="winner_confirmation")
  private char isWinner;

  @Column(name="winner_team")
  private String winnerTeam;

  public UserMatchKey getId() {
    return id;
  }

  public void setId(UserMatchKey id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Match getMatch() {
    return match;
  }

  public void setMatch(Match match) {
    this.match = match;
  }

  public char isWinner() {
    return isWinner;
  }

  public void setWinner(char winner) {
    isWinner = winner;
  }

  public String getWinnerTeam() {
    return winnerTeam;
  }

  public void setWinnerTeam(String winnerTeam) {
    this.winnerTeam = winnerTeam;
  }
}