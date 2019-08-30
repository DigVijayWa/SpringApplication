package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.model.bean.Match;
import com.SpringAppVersion2.model.bean.MatchType;
import com.SpringAppVersion2.result.MatchResultObject;
import com.SpringAppVersion2.result.Result;
import com.SpringAppVersion2.service.MatchService;
import com.SpringAppVersion2.spring.dao.MatchRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

  @Autowired
  MatchService matchService;

  private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

  Logger logger = LoggerFactory.getLogger(ProfileController.class);

  private Random random = new Random();

  @RequestMapping(value = "/api/match/get-specific/{matchId}")
  @ResponseBody
  public MatchResultObject getSpecificMatch(@PathVariable Long matchId) {

      return matchService.getSpecificMatch(matchId);
  }

  //api to create a match

  @RequestMapping(value = "/api/match/create-match/{matchType}/{matchDate}/{teamOne}/{teamTwo}")
  @ResponseBody
  public Result createMatch(@PathVariable String matchType, @PathVariable String matchDate, @PathVariable String teamOne, @PathVariable String teamTwo) {

    MatchType matchType1 = (matchType.compareToIgnoreCase("SINGLE") == 0) ? MatchType.SINGLE : MatchType.DOUBLE;
    Date matchDate1 = null;
    try {
      matchDate1 = dateFormatter.parse(matchDate);
    }catch(ParseException E) {
      E.printStackTrace();
    }

    Match match = new Match();

    match.setId((long)(Math.floor(Math.random()*1000000)+1000000));
    match.setMatchDate(matchDate1);
    match.setMatchType(matchType1);
    match.setTeamOne(teamOne);
    match.setTeamTwo(teamTwo);
    match.setWinnerTeam("");

    logger.info("CREATING MATCH");

    return matchService.createMatch(match);
  }

  //api to display all the matches under a user
  @RequestMapping(value = "/api/match/display-all/{userName}")
  @ResponseBody
  public List<MatchResultObject> displayAllMatchesForUser(@PathVariable String userName) {
    return matchService.displayAllMatches(userName);
  }

  //api to add a winner
  @RequestMapping(value = "/api/match/add-winner/{matchId}/{userId}/{winnerTeam}")
  @ResponseBody
  public Result updateWinner(@PathVariable Long matchId, @PathVariable Long userId, @PathVariable String winnerTeam) {
    return matchService.updateWinner(userId, matchId, winnerTeam);
  }
}
