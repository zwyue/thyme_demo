package com.zhu.thyme_demo.service.serviceImpl;

import com.zhu.thyme_demo.entity.UserPerm;
import com.zhu.thyme_demo.mapper.PermMapper;
import com.zhu.thyme_demo.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 14:10
 * @Description:
 */
@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper ;

    @Override
    public Integer addPerm(UserPerm userPerm) {
        return permMapper.addPerm(userPerm);
    }

    @Override
    public List<UserPerm> queryPerm() {
        return permMapper.queryPerm();
    }

    @Override
    public Integer assignPerm(Integer roleId, Integer[] permIds) {

        String permString = "";
        for (Integer perm:permIds) {
            permString += perm + "," ;
        }

        //查询角色权限表是否存在该角色，及角色是否被分配过权限
        Integer roleCount = permMapper.countPermByRoleId(roleId);

        Integer operateTimes = null ;

        Map map = new HashMap();
        map.put("permIds",permString.substring(0,permString.length()-1));
        map.put("roleId",roleId);

        if(roleCount==0){
            operateTimes = permMapper.addRolePermRelation(map);
        }else {
            operateTimes = permMapper.updateRolePermRelation(map);
        }

        return operateTimes;
    }

    @Override
    public Integer deletePerm(List<Integer> permIds) {
        Integer operateTimes = permMapper.deletePerm(permIds);
        return operateTimes;
    }
}
