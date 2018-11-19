package com.zhu.thyme_demo.config.shiro;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Auther: Joanne
 * @Date: 2018/11/14 09:01
 * @Description:
 */
@Getter
@Setter
//public class UserToken extends UsernamePasswordToken {
public class UserToken {

    private String loginType ;

//    public UserToken(final String username, final String password, String loginType){
//        super(username,password);
//        this.loginType = loginType ;
//    }
}
