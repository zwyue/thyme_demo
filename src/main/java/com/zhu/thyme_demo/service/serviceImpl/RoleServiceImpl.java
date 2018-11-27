package com.zhu.thyme_demo.service.serviceImpl;

import com.zhu.thyme_demo.entity.Faculty;
import com.zhu.thyme_demo.entity.UserRole;
import com.zhu.thyme_demo.mapper.RoleMapper;
import com.zhu.thyme_demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 09:19
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper ;

    @Override
    public Integer addRole(UserRole role) {
        return roleMapper.addRole(role);
    }

    @Override
    public List<UserRole> queryRole() {
        return roleMapper.queryRole();
    }

    @Override
    public Integer assignRole(Integer facultyId, Integer[] roleIds) {
        //组装角色数组为以逗号拼接的角色string，形如"1,2,3";
        String roleString = "" ;

        for (Integer role :roleIds) {
            roleString += role + "," ;
        }
        Map map = new HashMap();
        map.put("facultyId",facultyId);
        map.put("roleIds",roleString.substring(0,roleString.length()-1));
        //查询用户角色表中是否存在该用户，即该用户是否已经被分配过角色
        Integer facultyCount = roleMapper.countFacultyByFacultyId(facultyId);

        Integer operateTimes = 0;

        //未分配过角色
        if(facultyCount==0){
            //插入人员角色关系
            operateTimes = roleMapper.addFacultyRoleRelation(map);
        }else {
            //修改人员角色关系
            operateTimes = roleMapper.updateFacultyRoleRelation(map);
        }

        return operateTimes;
    }
}
