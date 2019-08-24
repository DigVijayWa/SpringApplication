package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.result.UserResultObject;
import com.SpringAppVersion2.spring.dao.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public UserResultObject findByUserName(String userName) {
    Optional<User> user = userRepository.findByUserName(userName);

    UserResultObject userResultObject = new UserResultObject();

    if (user != null) {
      userResultObject = setUserResultObject(user.get());
    }
    return userResultObject;
  }
  public List<UserResultObject> getAllUsers() {

    List<User> users = userRepository.findAll();
    List<UserResultObject> userResultObjects = new ArrayList<>();

    if(users != null) {

      for(User user : users) {
        UserResultObject userResultObject = new UserResultObject();
        userResultObject = setUserResultObject(user);
        userResultObjects.add(userResultObject);
      }
    }
    return userResultObjects;
  }
  private UserResultObject setUserResultObject(User user) {

    UserResultObject userResultObject = new UserResultObject();

    userResultObject.setTotalBookingsPossible(user.getTotalBookingsPossible());
    userResultObject.setUserEmail(user.getUserEmail());
    userResultObject.setUserId(user.getId());
    userResultObject.setUserName(user.getUserName());

    return userResultObject;
  }
}
