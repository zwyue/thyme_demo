package com.zhu.thyme_demo.mapper;

import com.zhu.thyme_demo.entity.UserPerm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 14:13
 * @Description:
 */
@Repository
public interface PermMapper {

    Integer addPerm(UserPerm userPerm);

    List<UserPerm> queryPerm();
}
