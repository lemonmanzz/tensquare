package com.tensquare.spit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spit implements Serializable {

    private String _id;  //ID
    private String content; //吐槽内容
    private String publishtime; // 发布日期
    private String userid; //发布人ID
    private String nickname;//发布人昵称
    private Integer visits;// 浏览量
    private Integer thumbup; //点赞数
    private Integer share; // 分享数
    private Integer comment;// 回复数
    private String state; // 是否可见
    private String parentid;//上级ID
}
