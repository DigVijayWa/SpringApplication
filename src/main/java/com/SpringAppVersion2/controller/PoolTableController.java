package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.result.PoolTableResultObject;
import com.SpringAppVersion2.result.Result;
import com.SpringAppVersion2.service.PoolTableService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PoolTableController {

  @Autowired
  PoolTableService poolTableService;

  @RequestMapping(value = "/api/pool-table/get-all-available/{startTime}")
  @ResponseBody
  public List<PoolTableResultObject> getAllAvailablePoolTableWithTimeSlot(
      @PathVariable int startTime) {
    return poolTableService.getAllAvailablePoolTablesWithInputTime(startTime);
  }

  @RequestMapping(value = "/api/pool-table/get-specific-available/{buildingId}/{floorNo}/{startTime}")
  @ResponseBody
  public List<PoolTableResultObject> getAllAvailablePoolTableWithBuildingIdTimeSlotFloorNo(
      @PathVariable Long buildingId, @PathVariable int floorNo, @PathVariable int startTime) {
    return poolTableService
        .getPoolTableBasedOnBuildingIdFloorNoTimeSlot(buildingId, floorNo, startTime);
  }

  @RequestMapping(value = "/api/pool-table/book-pool-table/{userId}/{poolId}/{startTime}")
  @ResponseBody
  public Result bookPoolTableWithUserIdPoolIdAndStartTime(@PathVariable Long userId,
      @PathVariable Long poolId, @PathVariable int startTime) {
    return poolTableService.bookPoolTableWithUserIdPoolIdAndStartTime(userId, poolId, startTime);
  }
}
