package com.SpringAppVersion2.model.bean;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user_game_object")
public class UserGameObject implements Serializable {

    private static final long serialVersionUID = 8557843374441515748L;


    @EmbeddedId
    UserGameObjectKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("game_id")
    @JoinColumn(name = "game_id")
    GameObject gameObject;

    @Column(name = "start_time")
    int startTime;

    @Column(name = "end_time")
    int endTime;

    @Column(name = "booking_date")
    Date bookingDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserGameObjectKey getId() {
        return id;
    }

    public void setId(UserGameObjectKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public int getStartTime() {
        return startTime;
    }

    public Date getBookingDate() { return bookingDate; }

    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

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


