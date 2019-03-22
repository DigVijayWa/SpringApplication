package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.result.BuildingResultObject;
import com.SpringAppVersion2.spring.dao.BuildingRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class BuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    public BuildingResultObject fetchFloorCountOfBuilding(Long buildingId) {

        Building building = buildingRepository.getOne(buildingId);

        BuildingResultObject buildingResultObject = new BuildingResultObject();

        if(building == null) {
            return buildingResultObject;
        }

        buildingResultObject = setBuildingResultObject(building);

        return buildingResultObject;
    }

    public List<BuildingResultObject> fetchBuildingList() {
        List<Building> buildingList = buildingRepository.findAll();

        List<BuildingResultObject> buildingResultObjectList = new ArrayList<>();

        if(CollectionUtils.isEmpty(buildingList)) {
            return Collections.emptyList();
        }

        for(Building building : buildingList) {

            buildingResultObjectList.add(setBuildingResultObject(building));
        }

        return buildingResultObjectList;

    }
    private BuildingResultObject setBuildingResultObject(Building building) {

        BuildingResultObject buildingResultObject = new BuildingResultObject();

        buildingResultObject.setBuildingId(building.getId());
        buildingResultObject.setFloorCount(building.getFloorCount());
        buildingResultObject.setBuildingName(building.getBuildingName());
        buildingResultObject.setBuildingNo(building.getBuildingNo());

        return buildingResultObject;
    }

}
