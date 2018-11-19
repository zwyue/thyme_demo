package com.zhu.thyme_demo.service.ServiceImpl;

import com.zhu.thyme_demo.entity.UserRole;
import com.zhu.thyme_demo.mapper.RoleMapper;
import com.zhu.thyme_demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
