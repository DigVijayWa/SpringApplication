package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserPoolTable;
import com.SpringAppVersion2.result.BookedPoolTableResultObject;
import com.SpringAppVersion2.result.UserResultObject;
import com.SpringAppVersion2.spring.dao.UserPoolTableRepository;
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
  UserPoolTableRepository userPoolTableRepository;

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
      List<UserPoolTable> userPoolTableList = userPoolTableRepository.findByUser(user.get());

      for(UserPoolTable userPoolTable : userPoolTableList) {

        bookedPoolTableResultObjectList.add(mapUserPoolTableToBookedPoolTable(userPoolTable));
      }

    }
    return bookedPoolTableResultObjectList;
  }

  private BookedPoolTableResultObject mapUserPoolTableToBookedPoolTable(UserPoolTable userPoolTable) {
    BookedPoolTableResultObject bookedPoolTableResultObject = new BookedPoolTableResultObject();

    bookedPoolTableResultObject.setBookingDate(userPoolTable.getBookingDate().toString());
    bookedPoolTableResultObject.setBuildingName(userPoolTable.getPoolTable().getBuilding().getBuildingName());
    bookedPoolTableResultObject.setPoolName(userPoolTable.getPoolTable().getPoolName());
    bookedPoolTableResultObject.setFloorNo(userPoolTable.getPoolTable().getFloorNo());
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
