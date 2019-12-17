package com.tensquare.article.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_channel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel implements Serializable {

  @Id
  private String id;
  private String name;
  private String state;
}
