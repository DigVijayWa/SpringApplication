package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.result.BookedPoolTableResultObject;
import com.SpringAppVersion2.result.UserResultObject;
import com.SpringAppVersion2.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  @Autowired
  UserService userService;

  @RequestMapping(value = "/api/user/by-user-name/{userName}")
  @ResponseBody
  public UserResultObject getUserByUserName(@PathVariable String userName) {
    return userService.findByUserName(userName);
  }

  @RequestMapping(value = "/api/user/all-users")
  @ResponseBody
  public List<UserResultObject> getAllUsers() {
    return userService.getAllUsers();
  }

  //api to display all the pool tables booked by the given user.
  @RequestMapping(value = "/api/user/get-booked-pool-tables-for-user/{userName}")
  @ResponseBody
  public List<BookedPoolTableResultObject> getAllPoolTablesBookedByTheUser(@PathVariable String userName) {
    return userService.getAllBookedPoolTablesByUserName(userName);
  }
}
