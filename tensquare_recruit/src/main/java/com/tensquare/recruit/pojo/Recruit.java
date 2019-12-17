package com.tensquare.recruit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_recruit")
public class Recruit implements Serializable {

  @Id
  private String id;
  private String jobname;
  private String salary;
  private String condition;
  private String education;
  private String type;
  private String address;
  private String eid;
  private java.sql.Timestamp createtime;
  private String state;
  private String url;
  private String label;
  private String content1;
  private String content2;
}
