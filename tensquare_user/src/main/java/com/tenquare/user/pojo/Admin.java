package com.tenquare.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_admin")
@Data
public class Admin {

  @Id
  private String id;
  private String loginname;
  private String password;
  private String state;

}
