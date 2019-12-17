package com.tensquare.base.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_label")
@Entity
public class Label implements Serializable {
    @Id
    private String id; //   标签ID
    private String labelname;//标签名称
    private String state;   //状态
    private BigInteger count;  //使用数量
    private String recommend; //是否推荐
}
