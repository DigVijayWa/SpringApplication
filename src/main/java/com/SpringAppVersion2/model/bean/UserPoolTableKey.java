package com.SpringAppVersion2.model.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserPoolTableKey implements Serializable {

    private static final long serialVersionUID = -4570797692426852060L;

    @Column(name="pool_id")
    Long poolId;

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

    public Long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
