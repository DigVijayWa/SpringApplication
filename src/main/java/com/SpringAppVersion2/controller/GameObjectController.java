package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.result.GameObjectResultObject;
import com.SpringAppVersion2.result.Result;
import com.SpringAppVersion2.service.GameObjectService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GameObjectController {

  @Autowired
  GameObjectService gameObjectService;

  @RequestMapping(value = "/api/game-object/get-all-available/{gameObjectClass}/{startTime}/{bookingDate}")
  @ResponseBody
  public List<GameObjectResultObject> getAllAvailablePoolTableWithTimeSlot(
      @PathVariable String gameObjectClass,
      @PathVariable int startTime, @PathVariable String bookingDate) {
    return gameObjectService.getAllAvailablePoolTablesWithInputTime(gameObjectClass,startTime,bookingDate);
  }

  @RequestMapping(value = "/api/game-object/get-specific-available/{gameObjectClass}/{buildingId}/{floorNo}/{startTime}/{bookingDate}")
  @ResponseBody
  public List<GameObjectResultObject> getAllAvailablePoolTableWithBuildingIdTimeSlotFloorNo(
      @PathVariable String gameObjectClass,
      @PathVariable Long buildingId, @PathVariable int floorNo, @PathVariable int startTime,  @PathVariable String bookingDate) {
    return gameObjectService
        .getPoolTableBasedOnBuildingIdFloorNoTimeSlot(gameObjectClass,buildingId, floorNo, startTime, bookingDate);
  }

  @RequestMapping(value = "/api/game-object/book-pool-table/{userId}/{poolId}/{startTime}/{bookingDate}")
  @ResponseBody
  public Result bookPoolTableWithUserIdPoolIdAndStartTime(@PathVariable Long userId,
      @PathVariable Long poolId, @PathVariable int startTime, @PathVariable String bookingDate) {
    return gameObjectService.bookPoolTableWithUserIdPoolIdAndStartTime(userId, poolId, startTime,bookingDate);
  }
}
