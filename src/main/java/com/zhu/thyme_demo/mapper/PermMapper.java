package com.zhu.thyme_demo.mapper;

import com.zhu.thyme_demo.entity.UserPerm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 14:13
 * @Description:
 */
@Repository
public interface PermMapper {

    Integer addPerm(UserPerm userPerm);

    List<UserPerm> queryPerm();

    Integer countPermByRoleId(Integer roleId);

    Integer addRolePermRelation(Map map);

    Integer updateRolePermRelation(Map map);

    Integer deletePerm(List<Integer> permIds);
}
