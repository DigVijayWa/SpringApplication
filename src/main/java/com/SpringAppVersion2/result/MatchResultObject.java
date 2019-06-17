package com.SpringAppVersion2.result;

import com.SpringAppVersion2.model.bean.MatchType;
import java.util.Date;

public class MatchResultObject {

  private Long matchId;

  private MatchType matchType;

  private Date matchDate;

  private String teamOne;

  private String teamTwo;

  private String winnerTeam;

  private char winnerFlag;

  public char getWinnerFlag() { return winnerFlag; }

  public void setWinnerFlag(char winnerFlag) { this.winnerFlag = winnerFlag; }

  public Long getMatchId() {
    return matchId;
  }

  public void setMatchId(Long matchId) {
    this.matchId = matchId;
  }

  public MatchType getMatchType() {
    return matchType;
  }

  public void setMatchType(MatchType matchType) {
    this.matchType = matchType;
  }

  public Date getMatchDate() { return matchDate; }

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

  public String getWinnerTeam() { return winnerTeam; }

  public void setWinnerTeam(String winnerTeam) { this.winnerTeam = winnerTeam; }
}
