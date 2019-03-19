package com.SpringAppVersion2.service;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.spring.dao.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    public Building fetchFloorCountOfBuilding(Long buildingId) {

        Building building = buildingRepository.getOne(buildingId);

        if(building == null) {
             //do something wise.
        }

        return building;
    }
}
