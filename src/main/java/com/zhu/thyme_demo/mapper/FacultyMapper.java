package com.zhu.thyme_demo.mapper;

import com.zhu.thyme_demo.entity.Faculty;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Joanne
 * @Date: 2018/11/16 09:28
 * @Description:
 */
@Repository
public interface FacultyMapper {
    Faculty queryFacultyByName(String name);

    Integer addFaculty(Faculty faculty);

    int countFacultyByRealName(String realName);

    String lastFacultyNo();

    List<Faculty> queryFaculty();
}
