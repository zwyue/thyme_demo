package com.zhu.thyme_demo.service.ServiceImpl;

import com.zhu.thyme_demo.entity.Faculty;
import com.zhu.thyme_demo.mapper.FacultyMapper;
import com.zhu.thyme_demo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Joanne
 * @Date: 2018/11/16 09:25
 * @Description:
 */
@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyMapper facultyMapper;

    @Override
    public Faculty queryFacultyByName(String name) {
        return facultyMapper.queryFacultyByName(name);
    }

    @Override
    public Integer addFaculty(Faculty faculty) {
        return facultyMapper.addFaculty(faculty);
    }

    @Override
    public int countFacultyByRealName(String realName) {
        return facultyMapper.countFacultyByRealName(realName);
    }

    @Override
    public String lastFacultyNo() {
        return facultyMapper.lastFacultyNo();
    }
}
