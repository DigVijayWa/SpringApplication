package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.model.bean.GameObject;
import com.SpringAppVersion2.model.bean.GameObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameObjectRepository extends JpaRepository<GameObject,Long>{

    List<GameObject> findByGameObjectClassAndBuildingAndFloorNo(String gameObjectClass,Building building, int floorNo);

    List<GameObject> findByGameObjectClass(String gameObjectClass);
}
