package com.SpringAppVersion2.spring.dao;

import com.SpringAppVersion2.model.bean.PoolTable;
import com.SpringAppVersion2.model.bean.User;
import com.SpringAppVersion2.model.bean.UserPoolTable;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPoolTableRepository extends JpaRepository<UserPoolTable,Long>{

    List<UserPoolTable> findByPoolTableAndStartTime(PoolTable poolTable,int startTime);

    List<UserPoolTable> findByPoolTableAndStartTimeAndBookingDate(PoolTable poolTable,int startTime, Date bookingDate);

    List<UserPoolTable> findByUser(User user);
}