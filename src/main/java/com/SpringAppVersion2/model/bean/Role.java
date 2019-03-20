package com.SpringAppVersion2.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="role")
public class Role {
  @Id
  @Column(name="role_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @NaturalId
  @Column(length = 60,name="role_name")
  private RoleName name;

  public Role() {

  }

  public Role(RoleName name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RoleName getName() {
    return name;
  }

  public void setName(RoleName name) {
    this.name = name;
  }
}
