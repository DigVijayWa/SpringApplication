package com.SpringAppVersion2.model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="match")
public class Match implements Serializable {

  private static final long serialVersionUID = 467031807600493117L;

  @Column(name="match_id")
  @Id
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length=60, name="match_type")
  private MatchType matchType;

  @Column(name="match_date")
  private Date matchDate;

  @Column(name="team_1")
  private String teamOne;

  @Column(name="team_2")
  private String teamTwo;

  @Column(name="winner_team")
  private String winnerTeam;

  @Column(name="created_by")
  private Long createdBy;

  public Match() {

  }

  public Match(Long id, MatchType matchType, Date matchDate, String teamOne, String teamTwo) {

    this.id = id;
    this.matchType = matchType;
    this.matchDate = matchDate;
    this.teamOne = teamOne;
    this.teamTwo = teamTwo;
  }

  public Match(Long id, MatchType matchType, Date matchDate, String teamOne, String teamTwo, String winnerTeam) {

    this.id = id;
    this.matchType = matchType;
    this.matchDate = matchDate;
    this.teamOne = teamOne;
    this.teamTwo = teamTwo;
    this.winnerTeam = winnerTeam;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MatchType getMatchType() {
    return matchType;
  }

  public void setMatchType(MatchType matchType) {
    this.matchType = matchType;
  }

  public Date getMatchDate() {
    return matchDate;
  }

  public void setMatchDate(Date matchDate) {
    this.matchDate = matchDate;
  }

  public String getTeamOne() {
    return teamOne;
  }

  public void setTeamOne(String teamOne) {
    this.teamOne = teamOne;
  }

  public String getTeamTwo() {
    return teamTwo;
  }

  public void setTeamTwo(String teamTwo) {
    this.teamTwo = teamTwo;
  }

  public String getWinnerTeam() {
    return winnerTeam;
  }

  public void setWinnerTeam(String winnerTeam) {
    this.winnerTeam = winnerTeam;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long user_id) {
    this.createdBy = createdBy;
  }

}
