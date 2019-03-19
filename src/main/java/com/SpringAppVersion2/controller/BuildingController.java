package com.SpringAppVersion2.controller;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @RequestMapping(value="api/building/floor-count/{buildingId}")
    public Building fetchFloorCountOfBuilding(@PathVariable Long buildingId) {

        return buildingService.fetchFloorCountOfBuilding(buildingId);
    }
}
