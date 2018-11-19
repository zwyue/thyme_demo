package com.zhu.thyme_demo.service;

import com.zhu.thyme_demo.entity.UserRole;

import java.util.List;

public interface RoleService {
    Integer addRole(UserRole role);

    List<UserRole> queryRole();
}
