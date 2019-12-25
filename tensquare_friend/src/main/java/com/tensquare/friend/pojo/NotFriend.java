package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_notfriend")
@IdClass(NotFriend.class)
public class NotFriend implements Serializable {

  @Id
  private String userid;
  @Id
  private String friendid;
}
