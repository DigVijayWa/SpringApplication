package com.SpringAppVersion2.model.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserGameObjectKey implements Serializable {

    private static final long serialVersionUID = -4570797692426852060L;

    @Column(name="game_object_id")
    Long gameObjectId;

    @Column(name="user_id")
    Long userId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getGameObjectId() {
        return gameObjectId;
    }

    public void setGameObjectId(Long gameObjectId) {
        this.gameObjectId = gameObjectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
