package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.payload.ApiResponse;
import com.SpringAppVersion2.service.JavaCustomMailSender;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
  //"i353455","roshan",new Date(),"poolTable","Building4","4","SINGLE","8-9"

  @Autowired
  JavaCustomMailSender javaCustomMailSender;

  @RequestMapping(value="/api/mail/send-mail/{teamOne}/{teamTwo}/{bookingDate}/{poolName}/{buildingName}/{floorNo}/{matchType}/{timeSlot}")
  @ResponseBody
  public ApiResponse sendMails(@PathVariable String teamOne,@PathVariable String teamTwo, @PathVariable String bookingDate,
      @PathVariable String poolName,@PathVariable String buildingName,@PathVariable int floorNo,@PathVariable String matchType,@PathVariable int timeSlot) {

      return javaCustomMailSender.sendEmail(teamOne,teamTwo,bookingDate,poolName,buildingName,""+floorNo,matchType,""+timeSlot+"-"+(timeSlot+1));
  }

}
