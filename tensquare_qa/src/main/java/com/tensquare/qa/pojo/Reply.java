package com.tensquare.qa.pojo;

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
@Table(name = "tb_reply")
public class Reply implements Serializable {

  @Id
  private String id;
  private String problemid;
  private String content;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;
  private String userid;
  private String nickname;

}
