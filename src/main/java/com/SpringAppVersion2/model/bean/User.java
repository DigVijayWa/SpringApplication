package com.SpringAppVersion2.model.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "sys_user")
public class User implements Serializable {

  private static final long serialVersionUID = -6046995984770372176L;

  @Column(name = "user_id")
  @Id
  private Long id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_email")
  private String userEmail;

  @Column(name = "user_password")
  private String password;

  @Column(name = "total_bookings_possible")
  private int totalBookingsPossible;

  @OneToMany(mappedBy = "user")
  Set<UserGameObject> userGameObjects;

  @ManyToMany
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  Set<Role> roles;

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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public Set<UserGameObject> getUserPoolTables() {
    return userGameObjects;
  }

  public void setUserPoolTables(
      Set<UserGameObject> userGameObjects) {
    this.userGameObjects = userGameObjects;
  }


}
