package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.Match;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MatchRepository extends JpaRepository<Match, Long> {

  Optional<Match> findById(Long aLong);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(value="update match set winner_team=:winnerTeam where match_id =:matchId", nativeQuery = true)
  int updateWinnerTeam(@Param("matchId") Long matchId, @Param("winnerTeam") String winnerTeam);
}
