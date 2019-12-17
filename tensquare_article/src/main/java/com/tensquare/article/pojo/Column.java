package com.tensquare.article.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_column")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column implements Serializable {

  @Id
  private String id;
  private String name;
  private String summary;
  private String userid;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp checktime;
  private String state;

}
