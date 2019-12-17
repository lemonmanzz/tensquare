package com.tensquare.article.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

  @Id
  private String id;
  private String columnid;
  private String userid;
  private String title;
  private String content;
  private String image;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;
  private String ispublic;
  private String istop;
  private Long visits;
  private Long thumbup;
  private Long comment;
  private String state;
  private String channelid;
  private String url;
  private String type;

}
