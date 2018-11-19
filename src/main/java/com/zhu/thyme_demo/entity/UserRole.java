package com.zhu.thyme_demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 09:24
 * @Description:
 */
@Getter
@Setter
public class UserRole {
    private Integer id;
    private String roleName;
    private Integer roleStatus ;
    private String createTime ;
    private String updateTime ;
}
