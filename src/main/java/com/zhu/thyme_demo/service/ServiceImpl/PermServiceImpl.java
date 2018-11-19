package com.zhu.thyme_demo.service.ServiceImpl;

import com.zhu.thyme_demo.entity.UserPerm;
import com.zhu.thyme_demo.mapper.PermMapper;
import com.zhu.thyme_demo.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
