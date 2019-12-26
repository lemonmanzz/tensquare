package com.tensquare.base.pojo;

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
@Table(name = "tb_city")
public class City  {
    @Id
    private String id; //城市id
    private String name; //城市名称
    private String ishot;//是否是热点城市
}
