package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.Building;
import com.SpringAppVersion2.model.bean.PoolTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long>{

}
