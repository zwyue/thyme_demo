package com.zhu.thyme_demo.entity;

import lombok.Data;

/**
 * @Auther: Joanne
 * @Date: 2018/11/13 18:48
 * @Description:
 */
@Data
public class Faculty {
    private Integer id;
    private String realName ;
    private String facultyNo;
    private String salt;
    private String password;
    private Boolean isAdmin ;
    private Integer status ;
    private String updateTime ;
    private String createTime ;
}
