package com.zhu.thyme_demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: Joanne
 * @Date: 2018/11/13 18:50
 * @Description:
 */
@Getter
@Setter
public class Student {
    private Integer id ;
    private String stuName ;
    private String stuNo ;
    private String salt ;
    private String password ;
}
