package com.tensquare.qa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_pl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pl implements Serializable {

  @Id
  private String problemid;
  @Id
  private String labelid;
}
