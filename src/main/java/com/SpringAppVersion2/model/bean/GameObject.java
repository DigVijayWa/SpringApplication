package com.SpringAppVersion2.model.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="game_object")
public class GameObject implements Serializable {


    private static final long serialVersionUID = -3636582666360007177L;

    @Column(name = "game_object_id")
    @Id
    private Long id;

    @Column(name = "game_object_name")
    private String gameObjectName;

    @Column(name = "floor_no")
    private int floorNo;

    @Column(name = "game_object_condition")
    private String gameObjectCondition;

    @Column(name="game_object_class")
    private String gameObjectClass;

    @ManyToOne
    @JoinColumn(name = "building_id")
    Building building;

    @OneToMany(mappedBy = "gameObject") //object name in the userGameObject
    Set<UserGameObject> userGameObject;



    public GameObject() {
    }

    public GameObject(Long id, String gameObjectName, int floorNo, String gameObjectCondition) {
        this.id  = id;
        this.gameObjectName = gameObjectName;
        this.floorNo = floorNo;
        this.gameObjectCondition = gameObjectCondition;
    }

    public GameObject(Long id, String gameObjectName, int floorNo, String gameObjectCondition, String gameObjectClass) {
        this.id  = id;
        this.gameObjectName = gameObjectName;
        this.floorNo = floorNo;
        this.gameObjectCondition = gameObjectCondition;
        this.gameObjectClass = gameObjectClass;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameObjectName() {
        return gameObjectName;
    }

    public void setGameObjectName(String gameObjectName) {
        this.gameObjectName = gameObjectName;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getGameObjectCondition() {
        return gameObjectCondition;
    }

    public void setGameObjectCondition(String gameObjectCondition) {
        this.gameObjectCondition = gameObjectCondition;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set<UserGameObject> getUserGameObject() {
        return userGameObject;
    }

    public void setUserGameObject(Set<UserGameObject> userGameObject) {
        this.userGameObject = userGameObject;
    }

    public String getGameObjectClass() {
        return gameObjectClass;
    }

    public void setGameObjectClass(String gameObjectClass) {
        this.gameObjectClass = gameObjectClass;
    }
}

