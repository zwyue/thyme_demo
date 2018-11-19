package com.zhu.thyme_demo.config.base;

import com.zhu.thyme_demo.config.shiro.ShiroUtils;
import com.zhu.thyme_demo.entity.Faculty;

/**
 * @Auther: Joanne
 * @Date: 2018/11/16 15:09
 * @Description:
 */
public class BaseController {

    public Faculty getLoginFaculty(){
        return (Faculty) ShiroUtils.getSubject().getPrincipal();
    }
}
