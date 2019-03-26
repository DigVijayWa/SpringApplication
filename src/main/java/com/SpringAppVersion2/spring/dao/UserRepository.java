package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

  Optional<User> findByUserEmail(String userEmail);

  Optional<User> findByUserNameOrUserEmail(String userName, String userEmail);

  List<User> findByIdIn(List<Long> userIds);

  Optional<User> findByUserName(String userName);

  Boolean existsByUserName(String userName);

  Boolean existsByUserEmail(String userEmail);

}
