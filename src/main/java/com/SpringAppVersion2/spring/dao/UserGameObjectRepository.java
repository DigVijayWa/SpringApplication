package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.GameObject;
import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserGameObject;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameObjectRepository extends JpaRepository<UserGameObject,Long>{

    List<UserGameObject> findByPoolTableAndStartTime(GameObject poolTable,int startTime);

    List<UserGameObject> findByGameObjectAndStartTimeAndBookingDate(GameObject poolTable,int startTime, Date bookingDate);

    List<UserGameObject> findByUser(User user);
}