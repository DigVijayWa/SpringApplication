package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.Match;
import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserMatch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserMatchRepository extends JpaRepository<UserMatch, Long> {

  List<UserMatch> findByUser(User user);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(value="update sys_user_match set winner_team=:winnerTeam where user_id =:userId and match_id =:matchId", nativeQuery = true)
  int updateWinnerTeam(@Param("userId") Long userId, @Param("matchId") Long matchId, @Param("winnerTeam") String winnerTeam);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(value="update sys_user_match set winner_confirmation=:winnerConfirmation where user_id =:userId and match_id =:matchId", nativeQuery = true)
  int updateWinnerStatus(@Param("userId") Long userId, @Param("matchId") Long matchId, @Param("winnerConfirmation") char winnerConfirmation);

  List<UserMatch> findByMatch(Match match);
}
