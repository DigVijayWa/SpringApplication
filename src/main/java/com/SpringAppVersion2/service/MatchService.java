package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.Match;
import com.SpringAppVersion2.model.bean.MatchType;
import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserMatch;
import com.SpringAppVersion2.model.bean.UserMatchKey;
import com.SpringAppVersion2.result.MatchResultObject;
import com.SpringAppVersion2.result.Result;
import com.SpringAppVersion2.result.enumPack.Message;
import com.SpringAppVersion2.spring.dao.MatchRepository;
import com.SpringAppVersion2.spring.dao.UserMatchRepository;
import com.SpringAppVersion2.spring.dao.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

  @Autowired
  MatchRepository matchRepository;

  @Autowired
  UserMatchRepository userMatchRepository;

  @Autowired
  UserRepository userRepository;

  Logger logger = LoggerFactory.getLogger(MatchService.class);

  //this method will return the exact match called
  public MatchResultObject getSpecificMatch(Long id) {

    return mapMatchResultObject(matchRepository.getOne(id),'f');
  }

  //this method creates match
  public Result createMatch(Match match) {
    Result result = new Result();
    Optional<Match> match2 = matchRepository.findById(match.getId());
    if (match2.isPresent()) {
      logger.error("NOT A RANDOM NUMBER : id : " + match.getId() + " name : " + match.getTeamOne());
      result.setMessage(Message.CREATE_FAILURE);
      return result;
    } else {

      Match resultMatch = matchRepository.save(match);

      if (match == null) {
        logger.error("ERROR DURing CREATION");
        result.setMessage(Message.CREATE_FAILURE);
      } else {
        result.setMessage(Message.CREATE_SUCCESS);

        // also i need to create team one and team two entries in user_match table.
        // algorithm to extract all the team players
        // adding entry in match user.

        String allTeams = match.getTeamOne() + "," + match.getTeamTwo();
        String[] teamList = allTeams.split(",");

        for (String teamMember : teamList) {

          UserMatch userMatch = new UserMatch();

          UserMatchKey userMatchKey = new UserMatchKey();

          userMatch.setMatch(match);
          userMatch.setWinner('f');
          userMatch.setWinnerTeam("");
          Optional<User> user = userRepository.findByUserName(teamMember);
          if (user.isPresent()) {
            userMatch.setUser(user.get());

            userMatchKey.setUserId(user.get().getId());
            userMatchKey.setMatchId(match.getId());

            userMatch.setId(userMatchKey);
          } else {
            logger.error("SOMETHING WENT WRONG");
            result.setMessage(Message.CREATE_FAILURE);
            break;
          }
          userMatchRepository.save(userMatch);
        }

      }

      return result;
    }

  }

  //this method returns all the matches related to the userId
  public List<MatchResultObject> displayAllMatches(Long userId) {

    List<MatchResultObject> matchResultObjectList = new ArrayList<>();

    if (userRepository.findById(userId) == null) {

      return matchResultObjectList;
    } else {
      Optional<User> user = userRepository.findById(userId);

      List<UserMatch> userMatchList = userMatchRepository.findByUser(user.get());

      for (UserMatch userMatch : userMatchList) {

        matchResultObjectList.add(mapMatchResultObject(userMatch.getMatch(),userMatch.isWinner()));
      }

      return matchResultObjectList;
    }
  }

  //this method is used to update the winner
  public Result updateWinner(Long userId, Long matchId, String winnerTeam) {
    /*

    algorithm :
        1. check if match exists or not
        2. check if user exists or not
        3. update the winner team in the respective column
        4. check the count of winner suggestion with given match ID
        5. if winner suggestion is unanimous then extract the team members.
        6. and update all the sys_user match to true with that user and match
        7. update the winner team in match table with match ID.
     */
    Result result = new Result();
    Optional<Match> match = matchRepository.findById(matchId);
    //1
    if (!match.isPresent()) {
      logger.error("The Match does not exist for the user");
      result.setMessage(Message.CREATE_FAILURE);
      return result;
    }

    //2
    Optional<User> user = userRepository.findById(userId);

    if (!user.isPresent()) {
      logger.error("The User does not exists");
      result.setMessage(Message.CREATE_FAILURE);
      return result;
    }

    //3
    userMatchRepository.updateWinnerTeam(userId, matchId, winnerTeam);

    //4
    List<UserMatch> totalPlayerMatch = userMatchRepository.findByMatch(match.get());

    int counterResponses = getCountOfSubmittedResponses(totalPlayerMatch);

    logger.info("COUNT : "+counterResponses);
    //5
    if ((match.get().getMatchType() == MatchType.DOUBLE && counterResponses > 2)
        || (match.get().getMatchType() == MatchType.SINGLE && counterResponses > 1)) {

      //check for the same winner team
      String teamOne = match.get().getTeamOne();
      String teamTwo = match.get().getTeamTwo();

      int counterTeamOne = 0;
      int counterTeamTwo = 0;

      for (UserMatch userMatch : totalPlayerMatch) {

        String compareWinTeam = userMatch.getWinnerTeam();
        if (compareWinTeam.compareTo(teamOne) == 0) {
          counterTeamOne++;
        } else if (compareWinTeam.compareTo(teamTwo) == 0) {
          counterTeamTwo++;
        }
      }

      if (counterTeamOne > counterTeamTwo) {

        //team one is the winner
        String teamMembers[] = teamOne.split(",");

        //update match with winner team

        matchRepository.updateWinnerTeam(matchId, teamOne);

        for (String teamMember : teamMembers) {

          //check exists
          Optional<User> userOptional = userRepository.findByUserName(teamMember);
          if (!userOptional.isPresent()) {
            logger
                .error("ERROR WHILE INSERTING WINNER PLAYER : " + teamMember + " DOES NOT EXISTS");
          } else {
            userMatchRepository.updateWinnerStatus(userOptional.get().getId(), matchId, 'Y');
          }
        }

        teamMembers = teamTwo.split(",");

        for (String teamMember : teamMembers) {

          //check exists
          Optional<User> userOptional = userRepository.findByUserName(teamMember);
          if (!userOptional.isPresent()) {
            logger
                .error("ERROR WHILE INSERTING WINNER PLAYER : " + teamMember + " DOES NOT EXISTS");
          } else {
            userMatchRepository.updateWinnerStatus(userOptional.get().getId(), matchId, 'N');
          }
        }


      } else if (counterTeamTwo > counterTeamOne) {
        //team one is the winner
        String teamMembers[] = teamTwo.split(",");

        //update match with winner team

        matchRepository.updateWinnerTeam(matchId, teamTwo);

        for (String teamMember : teamMembers) {

          //check exists
          Optional<User> userOptional = userRepository.findByUserName(teamMember);
          if (!userOptional.isPresent()) {
            logger
                .error("ERROR WHILE INSERTING WINNER PLAYER : " + teamMember + " DOES NOT EXISTS");
          } else {
            userMatchRepository.updateWinnerStatus(userOptional.get().getId(), matchId, 'Y');
          }
        }

        teamMembers = teamOne.split(",");

        for (String teamMember : teamMembers) {

          //check exists
          Optional<User> userOptional = userRepository.findByUserName(teamMember);
          if (!userOptional.isPresent()) {
            logger
                .error("ERROR WHILE INSERTING WINNER PLAYER : " + teamMember + " DOES NOT EXISTS");
          } else {
            userMatchRepository.updateWinnerStatus(userOptional.get().getId(), matchId, 'N');
          }
        }

        //team 2 is the winner;
      }
      //if found update the winner team

      //add Y to all the userMatch records with winner team members.
    }

    result.setMessage(Message.CREATE_SUCCESS);
    return result;
  }

  private int getCountOfSubmittedResponses(List<UserMatch> userMatchList) {
    int counter = 0;
    for(UserMatch userMatch : userMatchList) {
      logger.info("Team WINNER : "+userMatch.getWinnerTeam());
      if((userMatch.getWinnerTeam() != null && Pattern.matches("([a-z]{1})([0-9]{6})((,{1})([a-z]{1})([0-9]{6})){0,1}",userMatch.getWinnerTeam()))) {
        counter++;
      }
    }
    return counter;
  }

  public MatchResultObject mapMatchResultObject(Match match, char winnerFlag) {

    MatchResultObject matchResultObject = new MatchResultObject();

    matchResultObject.setMatchId(match.getId());
    matchResultObject.setMatchDate(match.getMatchDate());
    matchResultObject.setTeamOne(match.getTeamOne());
    matchResultObject.setTeamTwo(match.getTeamTwo());
    matchResultObject.setMatchType(match.getMatchType());
    matchResultObject.setWinnerTeam(match.getWinnerTeam());

    return matchResultObject;
  }
}
