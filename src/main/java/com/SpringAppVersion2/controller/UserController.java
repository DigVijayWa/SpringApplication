package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.model.bean.User;
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
}
