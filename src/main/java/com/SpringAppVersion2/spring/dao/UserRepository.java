package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

  Optional<User> findByUserEmail(String userEmail);

  Optional<User> findById(Long id);

  Optional<User> findByUserNameOrUserEmail(String userName, String userEmail);

  List<User> findByIdIn(List<Long> userIds);

  Optional<User> findByUserName(String userName);

  Boolean existsByUserName(String userName);

  Boolean existsByUserEmail(String userEmail);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(value="update sys_user set total_bookings_possible=:bookingCount where user_id =:userId", nativeQuery = true)
  int updateBookingCount(@Param("userId") Long userId, @Param("bookingCount") int bookingCount);


}
