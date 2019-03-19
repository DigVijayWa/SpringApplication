package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.model.bean.PoolTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoolTableRepository extends JpaRepository<PoolTable,Long>{

    List<PoolTable> findByBuildingAndFloorNo(Building building, int floorNo);
}
