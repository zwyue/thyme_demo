package com.zhu.thyme_demo.entity;

import lombok.Data;

/**
 * @Auther: Joanne
 * @Date: 2018/11/13 18:50
 * @Description:
 */
@Data
public class Student {
    private Integer id ;
    private String stuName ;
    private String stuNo ;
    private String salt ;
    private String password ;
}
