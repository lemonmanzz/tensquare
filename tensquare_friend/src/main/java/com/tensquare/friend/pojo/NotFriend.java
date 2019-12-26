package com.tensquare.friend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_notfriend")
@IdClass(NotFriend.class)
public class NotFriend implements Serializable {

  @Id
  private String userid;
  @Id
  private String friendid;

}
