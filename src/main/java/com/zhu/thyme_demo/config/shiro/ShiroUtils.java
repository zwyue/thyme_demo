package com.zhu.thyme_demo.config.shiro;

import com.zhu.thyme_demo.config.shiro.ShiroConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: Joanne
 * @Date: 2018/11/14 09:14
 * @Description:
 */
public class ShiroUtils {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 返回线程绑定的Subject
     */
    public static Subject getSubject() {
        logger.info("当前用户..........");
        return SecurityUtils.getSubject();
    }
}
