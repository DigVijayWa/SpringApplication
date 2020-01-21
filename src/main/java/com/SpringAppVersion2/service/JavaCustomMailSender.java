package com.SpringAppVersion2.service;


import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.payload.ApiResponse;
import com.SpringAppVersion2.spring.dao.UserRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class JavaCustomMailSender {

  //here we have to have 2 types of mails
  // SINGLE , DOUBLE
  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private UserRepository userRepository;

  Logger logger = LoggerFactory.getLogger(MatchService.class);

  @Autowired
  ResourceLoader resourceLoader;

  public ApiResponse sendEmail(String teamOne, String teamTwo, String bookingDate, String poolTable,
      String buildingName, String floorNo, String matchType, String timeslot) {
    ApiResponse apiResponse = new ApiResponse(true,"Mail Sent Successfully");
    try {

      MimeMessage message = javaMailSender.createMimeMessage();

      // use the true flag to indicate you need a multipart message
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setFrom("poolmanagement20@gmail.com");
      logger.info("Match Type : "+matchType);
      String[] users = new String[3];
      String[] userEmail = getEmailsFromUserName(teamOne, teamTwo, users, matchType);
      logger.info("Email : "+userEmail[1]);
      String[] userOne;
      if (matchType.compareTo("SINGLE") == 0) {
        userOne = new String[1];
        userOne[0] = teamOne;
        sendMailsToUsers(helper, message, userEmail, users, bookingDate, poolTable, buildingName,
            floorNo, matchType, userOne[0], timeslot);

      } else {
        userOne = new String[3];
        userOne = teamOne.split(",");
        sendMailsToUsersDouble(helper, message, userEmail, users, bookingDate, poolTable,
            buildingName,
            floorNo, matchType, userOne[0], timeslot);
      }

      //helper.addInline("leftSideImage", file);
    } catch (Exception E) {
      E.printStackTrace();
      apiResponse.setSuccess(false);
      apiResponse.setMessage("Mail cannot be sent : check the email id of the members");
    }
    return apiResponse;

  }

  private void sendMailsToUsers(MimeMessageHelper helper, MimeMessage message, String[] userEmail,
      String users[], String bookingDate, String poolTable, String buildingName,
      String floorNo, String matchType, String user, String timeslot) throws Exception {

    int length = userEmail.length;

    //for (int i = 0; i < length; i++) {
      //logger.info("Email : "+userEmail[i]);
      helper.setTo(userEmail[0]);

      logger.info("To Email : "+userEmail[0]);

     /* ClassPathResource classPathResource = new ClassPathResource("pool1.jpg");
      //logger.info("PATH : " + classPathResource.getPath());

      InputStream inputStream = classPathResource.getInputStream();
      File file = File.createTempFile("pool_1", ".jpg");
      try {
        copyInputStreamToFile(inputStream, file);
      } finally {
        IOUtils.closeQuietly(inputStream);
      }*/

      helper.setSubject("Invitation for a Pool Match");
      helper.setText(
          "<html>"
              + "<body>"
              + "<div><strong>Hi " + users[0] + "," + "</strong></br>"
              + "<div>Your friend " + user + " has invited you to a ranked pool match.</div>"

              + "<div><strong>Details : </strong></br>"
              + "<div>Match Type    : " + matchType + "</div>"
              + "<div>Pool table    : " + poolTable + "</div>"
              + "<div>Building Name : " + buildingName + "</div>"
              + "<div>Floor Number  : " + floorNo + "</div>"
              + "<div>Date          : " + bookingDate + "</div>"
              + "<div>Time          : " + timeslot + "</div>"
              + "</div>"
              + "<div>"
              + "<div> Hope you will not run away from the challenge.</div>"
              + "</div>"
              + "<div>Happy Pooling, </div>"
              + "Pool Management Team."
              + "</div>"
              + "</body>"
              + "</html>", true);
      //helper.addInline("rightSideImage", file);

      javaMailSender.send(message);

  }

  private void sendMailsToUsersDouble(MimeMessageHelper helper, MimeMessage message,
      String[] userEmail,
      String users[], String bookingDate, String poolTable, String buildingName,
      String floorNo, String matchType, String user, String timeslot) throws Exception {

    int length = userEmail.length;

    for (int i = 0; i < length; i++) {

      helper.setTo(userEmail[i]);
      logger.info("Email : "+userEmail[i]+" Name : "+users[i]);
      ClassPathResource classPathResource = new ClassPathResource("pool1.jpg");
      //logger.info("PATH : " + classPathResource.getPath());

      InputStream inputStream = classPathResource.getInputStream();
      File file = File.createTempFile("pool_1", ".jpg");
      try {
        copyInputStreamToFile(inputStream, file);
      } finally {
        IOUtils.closeQuietly(inputStream);
      }

      helper.setSubject("Invitation for a Pool Match");

      String members = "<div>";
      for (int j = 0; j < users.length; j++) {

        if (users[j] != users[i]) {
          members += "<div> " + users[j] + " </div>";
        }
      }
      members += "</div>";

      helper.setText(htmlBody
          /*"<html>"
              + "<body>"
              + "<img src='cid:rightSideImage' style='width:1920px;height:1080px;'/>"
              + "<div><strong>Hi " + users[i] + "," + "</strong></br>"
              + "<div>Your friend " + user + " has invited you to a ranked pool match.</div>"

              + "<div><strong>Details : </strong></br>"
              + "<div>Match Type    : " + matchType + "</div>"
              + "<div>Pool table    : " + poolTable + "</div>"
              + "<div>Building Name : " + buildingName + "</div>"
              + "<div>Floor Number  : " + floorNo + "</div>"
              + "<div>Date          : " + bookingDate + "</div>"
              + "<div>Time          : " + timeslot + "</div>"
              + "</div>"
              + "<div>"
              + "<div> Hope you will not run away from the challenge.</div>"
              + "</div>"
              + "There will be other members too : "
              + members
              + "<div>Happy Pooling, </div>"
              + "Pool Management Team."
              + "</div>"
              + "</body>"
              + "</html>"*/, true);
      helper.addInline("rightSideImage", file);

      javaMailSender.send(message);
    }

  }

  private String[] getEmailsFromUserName(String teamOne, String teamTwo, String users[],
      String matchType) {
    String teamOneEmail[];

    if (matchType == "SINGLE") {
      users[0] = teamTwo;
      Optional<User> user = userRepository.findByUserName(teamTwo);
      teamOneEmail = new String[1];
      teamOneEmail[0] = user.get().getUserEmail();
      return teamOneEmail;
    }
    String teamUserOne[] = teamOne.split(",");
    String teamUserTwo[] = teamTwo.split(",");
    teamOneEmail = new String[3];

    int i = 0, k = 0;
    for (String userName : teamUserOne) {
      Optional<User> user = userRepository.findByUserName(userName);
      if (k != 0) {
        teamOneEmail[i] = user.get().getUserEmail();
        users[i++] = userName;
      }
      k++;
    }
    for (String userName : teamUserTwo) {
      Optional<User> user = userRepository.findByUserName(userName);

      teamOneEmail[i] = user.get().getUserEmail();
      users[i++] = userName;
    }
    return teamOneEmail;
  }

  private void copyInputStreamToFile(InputStream inputStream, File file)
      throws IOException {

    try (FileOutputStream outputStream = new FileOutputStream(file)) {

      int read;
      byte[] bytes = new byte[1024];

      while ((read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, read);
      }

      // commons-io
      //IOUtils.copy(inputStream, outputStream);

    }
  }








  private static String htmlBody = "<html>"+
      "<body>"+
      "HI"+
      "</body>"+
      "</html>";


}
