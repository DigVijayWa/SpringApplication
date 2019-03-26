package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.result.BuildingResultObject;
import com.SpringAppVersion2.service.BuildingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BuildingController {

  @Autowired
  BuildingService buildingService;

  @RequestMapping(value = "api/building/floor-count/{buildingId}")
  @ResponseBody
  public BuildingResultObject fetchFloorCountOfBuilding(@PathVariable Long buildingId) {

    return buildingService.fetchFloorCountOfBuilding(buildingId);
  }

  /*has future scope of just showing those buildings who have atleast one
    free pool table
   */
  @RequestMapping(value = "api/building/building-list")
  @ResponseBody
  public List<BuildingResultObject> fetchBuildingList() {
    return buildingService.fetchBuildingList();
  }
}
