package com.zhu.thyme_demo.service;

import com.zhu.thyme_demo.entity.Faculty;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: Joanne
 * @Date: 2018/11/16 09:24
 * @Description:
 */
public interface FacultyService {
    Faculty queryFacultyByName(String name);

    Integer addFaculty(Faculty faculty);

    int countFacultyByRealName(String realName);

    String lastFacultyNo();

    List<Faculty> queryFaculty();
}
