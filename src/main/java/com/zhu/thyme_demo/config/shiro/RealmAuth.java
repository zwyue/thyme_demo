package com.zhu.thyme_demo.config.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: Joanne
 * @Date: 2018/11/14 08:59
 * @Description:
 */
public class RealmAuth{
//public class RealmAuth extends ModularRealmAuthenticator {

    private static final Logger logger = LoggerFactory.getLogger(RealmAuth.class);

//    @Override
//    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
//        logger.info("UserModularRealmAuthenticator:method doAuthenticate() execute ");
//        // 判断getRealms()是否返回为空
//        assertRealmsConfigured();
//        // 强制转换回自定义的CustomizedToken
//        UserToken userToken = (UserToken) authenticationToken;
//        // 登录类型
//        String loginType = userToken.getLoginType();
//        // 所有Realm
//        Collection<Realm> realms = getRealms();
//        // 登录类型对应的所有Realm
//        List<Realm> typeRealms = new ArrayList<>();
//        for (Realm realm : realms) {
//            if (realm.getName().contains(loginType)){
//                ((ArrayList<Realm>) typeRealms).add(realm);
//            }
//        }
//        // 判断是单Realm还是多Realm
////        if (typeRealms.size() == 1){
////            logger.info("doSingleRealmAuthentication() execute ");
////            return doSingleRealmAuthentication(typeRealms.get(0), userToken);
////        }
////        else{
////            logger.info("doMultiRealmAuthentication() execute ");
////            return doMultiRealmAuthentication(typeRealms, userToken);
////        }
//        return null;
//    }
}
