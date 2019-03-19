package com.SpringAppVersion2.model.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="pool_table")
public class PoolTable implements Serializable {


    private static final long serialVersionUID = -3636582666360007177L;

    @Column(name = "pool_id")
    @Id
    private Long id;

    @Column(name = "pool_name")
    private String poolName;

    @Column(name = "floor_no")
    private int floorNo;

    @Column(name = "pool_condition")
    private String poolCondition;

    @ManyToOne
    @JoinColumn(name = "building_id")
    Building building;

    @OneToMany(mappedBy = "poolTable")
    Set<UserPoolTable> userPoolTables;

    public PoolTable() {
    }

    public PoolTable(Long id, String poolName, int floorNo, String poolCondition) {
        this.id  = id;
        this.poolName = poolName;
        this.floorNo = floorNo;
        this.poolCondition = poolCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getPoolCondition() {
        return poolCondition;
    }

    public void setPoolCondition(String poolCondition) {
        this.poolCondition = poolCondition;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}

