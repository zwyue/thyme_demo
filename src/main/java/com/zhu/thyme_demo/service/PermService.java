package com.zhu.thyme_demo.service;

import com.zhu.thyme_demo.entity.UserPerm;

import java.util.List;

public interface PermService {

    Integer addPerm(UserPerm userPerm);

    List<UserPerm> queryPerm();
}
