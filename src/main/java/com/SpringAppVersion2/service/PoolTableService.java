package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.model.bean.PoolTable;
import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserPoolTable;
import com.SpringAppVersion2.model.bean.UserPoolTableKey;
import com.SpringAppVersion2.result.PoolTableResultObject;
import com.SpringAppVersion2.result.Result;
import com.SpringAppVersion2.result.enumPack.Message;
import com.SpringAppVersion2.spring.dao.BuildingRepository;
import com.SpringAppVersion2.spring.dao.PoolTableRepository;
import com.SpringAppVersion2.spring.dao.UserPoolTableRepository;
import com.SpringAppVersion2.spring.dao.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PoolTableService {

  @Autowired
  PoolTableRepository poolTableRepository;

  @Autowired
  BuildingRepository buildingRepository;

  @Autowired
  UserPoolTableRepository userPoolTableRepository;

  @Autowired
  UserRepository userRepository;

  Log logger = LogFactory.getLog(PoolTableService.class);

  /*This method returns all available
   pool Tables with time slot as input*/
  public List<PoolTableResultObject> getAllAvailablePoolTablesWithInputTime(int startTime) {

    List<PoolTable> poolTableList = poolTableRepository.findAll();

    List<PoolTableResultObject> poolTableResultObjects = new ArrayList<>();

    if (poolTableList.isEmpty()) {
      logger.info("NO RECORDS FOUND FOR : Start time : [ " + startTime + "]");
      return Collections.emptyList();
    }

    getPoolTableResultObjectListFromPoolTableList(poolTableList, poolTableResultObjects, startTime);

    return poolTableResultObjects;
  }

  /*This method returns all the pool-tables available on the basis of building id
     floor no and start time
   */
  public List<PoolTableResultObject> getPoolTableBasedOnBuildingIdFloorNoTimeSlot(Long buildingId,
      int floorNo, int startTime) {

    List<PoolTableResultObject> poolTableResultObjects = new ArrayList<>();

    Building building = buildingRepository.getOne(buildingId);

    List<PoolTable> poolTableList = poolTableRepository.findByBuildingAndFloorNo(building, floorNo);

    if (poolTableList.isEmpty()) {
      logger.info("NO RECORDS FOUND FOR : Building Name : [ " + building.getBuildingName() + "]");
      return Collections.emptyList();
    }

    getPoolTableResultObjectListFromPoolTableList(poolTableList, poolTableResultObjects, startTime);

    return poolTableResultObjects;
  }
  /*
    This method books the pool table with userId, PoolId, and startTime
   */
  public Result bookPoolTableWithUserIdPoolIdAndStartTime(Long userId, Long poolId, int startTime) {

    Result result = new Result();

    PoolTable poolTable = poolTableRepository.getOne(poolId);

    if (poolTable == null) {
      result.setMessage(Message.CREATE_FAILURE);
    }

    User user = userRepository.getOne(userId);

    if (user == null) {
      result.setMessage(Message.CREATE_FAILURE);
    }

    if (isRecordExists(poolTable, startTime)) {
      result.setMessage(Message.CREATE_FAILURE);
    }

    if (result.getMessage() != null) {
      return result;
    } else {

      UserPoolTable userPoolTable = saveUserPoolTable(user, poolTable,startTime);

      if (userPoolTable == null) {
        result.setMessage(Message.CREATE_FAILURE);
      }

      result.setMessage(Message.CREATE_SUCCESS);

      return result;
    }
  }

  private UserPoolTable saveUserPoolTable(User user, PoolTable poolTable,int startTime) {
    UserPoolTable userPoolTable = new UserPoolTable();

    UserPoolTableKey userPoolTableKey = new UserPoolTableKey();
    userPoolTableKey.setPoolId(poolTable.getId());
    userPoolTableKey.setUserId(user.getId());

    userPoolTable.setPoolTable(poolTable);
    userPoolTable.setUser(user);
    userPoolTable.setId(userPoolTableKey);
    userPoolTable.setStartTime(startTime);
    int endTime = (startTime + 1) > 12 ? (startTime+1)-12 : (startTime+1);
    userPoolTable.setEndTime(endTime);

    UserPoolTable userPoolTable1 = userPoolTableRepository.save(userPoolTable);

    return userPoolTable1;

  }

  private boolean isRecordExists(PoolTable poolTable, int startTime) {
    List<UserPoolTable> userPoolTable = userPoolTableRepository
        .findByPoolTableAndStartTime(poolTable, startTime);

    if (userPoolTable.isEmpty()) {
      return false;
    }
    return true;
  }

  private void getPoolTableResultObjectListFromPoolTableList(List<PoolTable> poolTableList,
      List<PoolTableResultObject> poolTableResultObjects, int startTime) {
    for (PoolTable poolTable : poolTableList) {
      List<UserPoolTable> resultPoolTableList = userPoolTableRepository
          .findByPoolTableAndStartTime(poolTable, startTime);

      if (resultPoolTableList.isEmpty()) {
        poolTableResultObjects.add(getPoolTableResultObject(poolTable));
      }
    }
  }

  /*This method prepares the result object to be added in the list
    by takiing pool table as input.
   */
  private PoolTableResultObject getPoolTableResultObject(PoolTable poolTable) {
    PoolTableResultObject poolTableResultObject = new PoolTableResultObject();

    poolTableResultObject.setPoolId(poolTable.getId());
    poolTableResultObject.setPoolName(poolTable.getPoolName());
    poolTableResultObject.setPoolCondition(poolTable.getPoolCondition());
    poolTableResultObject.setFloorNo(poolTable.getFloorNo());
    poolTableResultObject.setStatus("AVAILABLE");
    poolTableResultObject.setBuildingNo(poolTable.getBuilding().getBuildingNo());
    poolTableResultObject.setBuildingName(poolTable.getBuilding().getBuildingName());

    return poolTableResultObject;
  }


}
