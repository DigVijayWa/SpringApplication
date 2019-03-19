package com.SpringAppVersion2.model.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user_pool_table")
public class UserPoolTable implements Serializable {

    private static final long serialVersionUID = 8557843374441515748L;


    @EmbeddedId
    UserPoolTableKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("pool_id")
    @JoinColumn(name = "pool_id")
    PoolTable poolTable;

    @Column(name = "start_time")
    int startTime;

    @Column(name = "end_time")
    int endTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserPoolTableKey getId() {
        return id;
    }

    public void setId(UserPoolTableKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PoolTable getPoolTable() {
        return poolTable;
    }

    public void setPoolTable(PoolTable poolTable) {
        this.poolTable = poolTable;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    // standard constructors, getters, and setters
}


