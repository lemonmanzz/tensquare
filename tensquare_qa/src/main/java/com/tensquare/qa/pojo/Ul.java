package com.tensquare.qa.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_ul")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ul implements Serializable {

  @Id
  private String uid;
  @Id
  private String lid;
}
