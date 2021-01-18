package com.zhu.thyme_demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Joanne
 * @date 2018/11/19 09:24
 */
@Data
public class UserRole {
    private Integer id;
    private String roleName;
    private Integer roleStatus ;
    private String createTime ;
    private String updateTime ;
}
