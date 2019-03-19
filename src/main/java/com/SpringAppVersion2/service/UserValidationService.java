package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.spring.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

  @Autowired
  UserRepository userRepository;

  public boolean isValidUser(Long userId, String password) {

    User user = userRepository.getOne(userId);

    if (user == null) {
      //User not found;
      return false;
    }

    if(!user.getPassword().equals(password)) {
      //Password not correct;
      return false;
    }

    return true;
  }
}
