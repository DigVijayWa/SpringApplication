package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.model.bean.GameObject;

import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserGameObject;

import com.SpringAppVersion2.model.bean.UserGameObjectKey;

import com.SpringAppVersion2.result.GameObjectResultObject;

import com.SpringAppVersion2.result.Result;
import com.SpringAppVersion2.result.enumPack.Message;
import com.SpringAppVersion2.spring.dao.BuildingRepository;
import com.SpringAppVersion2.spring.dao.GameObjectRepository;

import com.SpringAppVersion2.spring.dao.UserGameObjectRepository;

import com.SpringAppVersion2.spring.dao.UserRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GameObjectService {

  @Autowired
  GameObjectRepository gameObjectRepository;

  @Autowired
  BuildingRepository buildingRepository;

  @Autowired
  UserGameObjectRepository userGameObjectRepository;

  @Autowired
  UserRepository userRepository;

  Log logger = LogFactory.getLog(GameObjectService.class);

  /*This method returns all available
   pool Tables with time slot as input*/
  public List<GameObjectResultObject> getAllAvailablePoolTablesWithInputTime(String gameObjectClass,int startTime,String bookingDate) {

    List<GameObject> gameObjectList = gameObjectRepository.findByGameObjectClass(gameObjectClass);

    List<GameObjectResultObject> poolTableResultObjects = new ArrayList<>();
    Date bookingDateParsed = null;
    try{
      SimpleDateFormat formatter3=new SimpleDateFormat("MM-dd-yyyy");
      bookingDateParsed = formatter3.parse(bookingDate);
    }catch(ParseException e) {
      e.printStackTrace();
    }

    if (gameObjectList.isEmpty()) {
      logger.info("NO RECORDS FOUND FOR : Start time : [ " + startTime + "]");
      return Collections.emptyList();
    }

    getPoolTableResultObjectListFromPoolTableList(gameObjectList, poolTableResultObjects, startTime, bookingDateParsed);

    return poolTableResultObjects;
  }

  /*This method returns all the pool-tables available on the basis of building id
     floor no and start time
   */
  public List<GameObjectResultObject> getPoolTableBasedOnBuildingIdFloorNoTimeSlot(String gameObjectClass, Long buildingId,
      int floorNo, int startTime,String bookingDate) {

    List<GameObjectResultObject> poolTableResultObjects = new ArrayList<>();

    Building building = buildingRepository.getOne(buildingId);

    List<GameObject> poolTableList = gameObjectRepository.findByGameObjectClassAndBuildingAndFloorNo(gameObjectClass,building, floorNo);

    if (poolTableList.isEmpty()) {
      logger.info("NO RECORDS FOUND FOR : Building Name : [ " + building.getBuildingName() + "]");
      return Collections.emptyList();
    }

    Date bookingDateParsed = null;
    try{
      SimpleDateFormat formatter3=new SimpleDateFormat("MM-dd-yyyy");
      bookingDateParsed = formatter3.parse(bookingDate);
    }catch(ParseException e) {
      e.printStackTrace();
    }

    getPoolTableResultObjectListFromPoolTableList(poolTableList, poolTableResultObjects, startTime,bookingDateParsed);

    return poolTableResultObjects;
  }
  /*
    This method books the pool table with userId, PoolId, and startTime
   */
  public Result bookPoolTableWithUserIdPoolIdAndStartTime(Long userId, Long poolId, int startTime,String bookingDate) {

    Result result = new Result();

    Date bookingDateParsed = null;
    try{
      SimpleDateFormat formatter3=new SimpleDateFormat("MM-dd-yyyy");
      bookingDateParsed = formatter3.parse(bookingDate);
    }catch(ParseException e) {
      e.printStackTrace();
    }

    GameObject gameObject = gameObjectRepository.getOne(poolId);

    if (gameObject == null ) {
      result.setMessage(Message.CREATE_FAILURE);
    }

    User user = userRepository.getOne(userId);

    if (user == null || user.getTotalBookingsPossible() <= 0) {
      result.setMessage(Message.CREATE_FAILURE);
    }

    if (isRecordExists(gameObject, startTime,bookingDateParsed)) {
      result.setMessage(Message.CREATE_FAILURE);
    }

    if (result.getMessage() != null) {
      return result;
    } else {

      UserGameObject userPoolTable = saveUserPoolTable(user, gameObject,startTime,bookingDateParsed);

      if (userPoolTable == null) {
        result.setMessage(Message.CREATE_FAILURE);
      }else {
        userRepository.updateBookingCount(user.getId(), (user.getTotalBookingsPossible()-1));
        result.setMessage(Message.CREATE_SUCCESS);
      }
      return result;
    }
  }

  private UserGameObject saveUserPoolTable(User user, GameObject gameObject,int startTime,Date bookingDate) {
    UserGameObject userPoolTable = new UserGameObject();

    UserGameObjectKey userPoolTableKey = new UserGameObjectKey();
    userPoolTableKey.setGameObjectId(gameObject.getId());
    userPoolTableKey.setUserId(user.getId());

    userPoolTable.setGameObject(gameObject);
    userPoolTable.setUser(user);
    userPoolTable.setId(userPoolTableKey);
    userPoolTable.setStartTime(startTime);
    userPoolTable.setBookingDate(bookingDate);

    int endTime = (startTime + 1) > 12 ? (startTime+1)-12 : (startTime+1);
    userPoolTable.setEndTime(endTime);

    UserGameObject userPoolTable1 = userGameObjectRepository.save(userPoolTable);

    return userPoolTable1;

  }

  private boolean isRecordExists(GameObject gameObject, int startTime, Date bookingDate) {
    List<UserGameObject> userGameObjects = userGameObjectRepository
        .findByGameObjectAndStartTimeAndBookingDate(gameObject, startTime, bookingDate);

    if (userGameObjects.isEmpty()) {
      return false;
    }
    return true;
  }

  private void getPoolTableResultObjectListFromPoolTableList(List<GameObject> gameObjectList,
      List<GameObjectResultObject> poolTableResultObjects, int startTime, Date bookingDateParsed) {

    for (GameObject gameObject : gameObjectList) {

      List<UserGameObject> resultPoolTableList = userGameObjectRepository
          .findByGameObjectAndStartTimeAndBookingDate(gameObject, startTime,bookingDateParsed);

      if (resultPoolTableList.isEmpty()) {
        poolTableResultObjects.add(getGameObjectResultObject(gameObject));
      }
    }
  }

  /*This method prepares the result object to be added in the list
    by takiing pool table as input.
   */
  private GameObjectResultObject getGameObjectResultObject(GameObject gameObject) {
    GameObjectResultObject gameObjectResultObject = new GameObjectResultObject();

    gameObjectResultObject.setGameObjectId(gameObject.getId());
    gameObjectResultObject.setGameObjectName(gameObject.getGameObjectName());
    gameObjectResultObject.setGameObjectCondition(gameObject.getGameObjectCondition());
    gameObjectResultObject.setFloorNo(gameObject.getFloorNo());
    gameObjectResultObject.setStatus("AVAILABLE");
    gameObjectResultObject.setBuildingNo(gameObject.getBuilding().getBuildingNo());
    gameObjectResultObject.setBuildingName(gameObject.getBuilding().getBuildingName());

    return gameObjectResultObject;
  }


}
