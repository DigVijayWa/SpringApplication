package com.SpringAppVersion2.model.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = -6046995984770372176L;

    @Column(name = "user_id")
    @Id
    private Long id;

    @Column(name = "user_password")
    private String password;

    @Column(name = "total_bookings_possible")
    private int totalBookingsPossible;

    @OneToMany(mappedBy = "user")
    Set<UserPoolTable> userPoolTables;

    public int getTotalBookingsPossible() {
        return totalBookingsPossible;
    }

    public void setTotalBookingsPossible(int totalBookingsPossible) {
        this.totalBookingsPossible = totalBookingsPossible;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
