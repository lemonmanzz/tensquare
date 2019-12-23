package com.tenquare.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_user")
@Data
public class User {
  @Id
  private String id;
  private String loginname;
  private String mobile;
  private String password;
  private String nickname;
  private String sex;
  private java.sql.Timestamp birthday;
  private String avatar;
  private String email;
  private Date regdate;
  private Date updatedate;
  private Date lastdate;
  private Long online;
  private String interest;
  private String personality;
  private Long fanscount;
  private Long followcount;
}
