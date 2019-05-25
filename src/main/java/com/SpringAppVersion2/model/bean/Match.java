package com.SpringAppVersion2.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="match")
public class Match implements Serializable {

  private static final long serialVersionUID = 467031807600493117L;

  @Column(name="match_id")
  @Id
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length=60, name="match_type")
  private MatchType matchType;
  
}
