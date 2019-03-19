package com.SpringAppVersion2.model.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="building")
public class Building implements Serializable {

    private static final long serialVersionUID = -6466458310339466169L;

    @Column(name = "building_id")
    @Id
    private Long id;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "building_no")
    private int buildingNo;

    @Column(name = "floor_count")
    private int floorCount;

    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PoolTable> poolTables;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<PoolTable> getPoolTables() {
        return poolTables;
    }

    public void setPoolTables(Set<PoolTable> poolTables) {
        this.poolTables = poolTables;
    }
}
