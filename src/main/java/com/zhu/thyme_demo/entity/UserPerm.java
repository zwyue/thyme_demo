package com.zhu.thyme_demo.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 14:04
 * @Description:
 */
@Getter
@Setter
public class UserPerm {
    private Integer id ;

    private String permName;

    private String permForm ;

    private Integer permStatus;

    private Integer parentNode ;

    private String permAddress ;

    private String updateTime ;

    private String createTime ;
}
