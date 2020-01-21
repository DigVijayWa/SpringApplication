package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserGameObject;
import com.SpringAppVersion2.result.BookedPoolTableResultObject;
import com.SpringAppVersion2.result.UserResultObject;
import com.SpringAppVersion2.spring.dao.UserGameObjectRepository;
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

  @Autowired
  UserGameObjectRepository userGameObjectRepository;

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

  public List<BookedPoolTableResultObject> getAllBookedPoolTablesByUserName(String userName) {
    Optional<User> user = userRepository.findByUserName(userName);
    List<BookedPoolTableResultObject> bookedPoolTableResultObjectList = new ArrayList<>();

    if (user != null) {
      List<UserGameObject> userPoolTableList = userGameObjectRepository.findByUser(user.get());

      for(UserGameObject userPoolTable : userPoolTableList) {

        bookedPoolTableResultObjectList.add(mapUserPoolTableToBookedPoolTable(userPoolTable));
      }

    }
    return bookedPoolTableResultObjectList;
  }

  private BookedPoolTableResultObject mapUserPoolTableToBookedPoolTable(UserGameObject userPoolTable) {
    BookedPoolTableResultObject bookedPoolTableResultObject = new BookedPoolTableResultObject();

    bookedPoolTableResultObject.setBookingDate(userPoolTable.getBookingDate().toString());
    bookedPoolTableResultObject.setBuildingName(userPoolTable.getGameObject().getBuilding().getBuildingName());
    bookedPoolTableResultObject.setGameObjectName(userPoolTable.getGameObject().getGameObjectName());
    bookedPoolTableResultObject.setFloorNo(userPoolTable.getGameObject().getFloorNo());
    bookedPoolTableResultObject.setStartTime(userPoolTable.getStartTime());
    bookedPoolTableResultObject.setEndTime(userPoolTable.getEndTime());

    return bookedPoolTableResultObject;
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
