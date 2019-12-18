package com.tensquare.gathering.pojo;

import com.sun.xml.internal.ws.encoding.soap.SerializerConstants;
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
@Table(name = "tb_gathering")
public class Gathering implements Serializable {

  @Id
  private String id;
  private String name;
  private String summary;
  private String detail;
  private String sponsor;
  private String image;
  private java.sql.Timestamp starttime;
  private java.sql.Timestamp endtime;
  private String address;
  private java.sql.Timestamp enrolltime;
  private String state;
  private String city;

}
