package com.zhu.thyme_demo.mapper;

import com.zhu.thyme_demo.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    Integer addRole(UserRole role);

    List<UserRole> queryRole();
}
