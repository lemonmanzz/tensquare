package com.tensquare.recruit.pojo;

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
@Table(name = "tb_enterprise")
public class Enterprise implements Serializable {

  @Id
  private String id;
  private String name;
  private String summary;
  private String address;
  private String labels;
  private String coordinate;
  private String ishot;
  private String logo;
  private Long jobcount;
  private String url;


}
