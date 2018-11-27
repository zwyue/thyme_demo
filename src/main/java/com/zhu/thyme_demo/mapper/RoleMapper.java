package com.zhu.thyme_demo.mapper;

import com.zhu.thyme_demo.entity.Faculty;
import com.zhu.thyme_demo.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {

    Integer addRole(UserRole role);

    List<UserRole> queryRole();

    Integer countFacultyByFacultyId(Integer facultyId);

    Integer addFacultyRoleRelation(Map map);

    Integer updateFacultyRoleRelation(Map map);
}
