package com.zhu.thyme_demo.config.shiro;

import com.zhu.thyme_demo.config.exception.ExceptionEnum;
import com.zhu.thyme_demo.constant.SysConstant;
import com.zhu.thyme_demo.entity.Faculty;
import com.zhu.thyme_demo.service.FacultyService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.zhu.thyme_demo.constant.SysConstant.Admin.*;

/**
 * @Auther: Joanne
 * @Date: 2018/11/13 18:42
 * @Description:
 */
public class FacultyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(FacultyRealm.class);

    @Autowired
    private FacultyService facultyService ;

    @Override
    public  boolean isPermitted(PrincipalCollection principals, String permission){
        Faculty user = (Faculty) principals.getPrimaryPrincipal();
        return user.getIsAdmin()||super.isPermitted(principals,permission);
    }

    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        Faculty user = (Faculty)principals.getPrimaryPrincipal();
        return user.getIsAdmin()||super.hasRole(principals,roleIdentifier);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("身份验证....................");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());

        Faculty user = facultyService.queryFacultyByName(name);

        //数据库用户
        if(user != null){
            logger.info("........数据库用户：{}登陆..........",user.getRealName());
            user.setIsAdmin(SysConstant.Admin.IS_NOT_ADMIN);
        }

        //管理员
        if(USERNAME.equals(name)&&SysConstant.Admin.PASSWORD.equals(password)){
            logger.info(".........管理员穿透.........");
            user = new Faculty();
            user.setRealName(SysConstant.Admin.USERNAME);
            user.setIsAdmin(SysConstant.Admin.IS_ADMIN);
            user.setSalt(USERNAME);
            SimpleHash hash=new SimpleHash(MD5, new SimpleByteSource(PASSWORD),new SimpleByteSource(USERNAME),HASH_ITERATIONS);
            user.setPassword(hash.toHex());
        }

        //无此用户
        if (null == user) {
            logger.info("..........数据库与系统无此用户...........");
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );
    }
}
