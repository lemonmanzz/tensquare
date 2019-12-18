package com.tensquare.gathering.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usergath")
public class Usergath implements Serializable {

  @Id
  private String userid;
  @Id
  private String gathid;
  private java.sql.Timestamp exetime;


}
