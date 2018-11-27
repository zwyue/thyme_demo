package com.zhu.thyme_demo.controller;

import com.zhu.thyme_demo.entity.UserRole;
import com.zhu.thyme_demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 09:18
 * @Description:
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addRole",method = GET)
    public String addRole(){
        return "add_role";
    }

    @RequestMapping(value = "/addRole",method = POST)
    public String addRole(UserRole role){
//        role.setRoleStatus(1);
        if(!StringUtils.isEmpty(role.getRoleName())){
            roleService.addRole(role);
        }
        return "add_role";
    }

    @RequestMapping(value = "roleList",method = GET)
    public String roleList(Model model,Integer facultyId){
        List<UserRole> roleList = roleService.queryRole();
        model.addAttribute("roleList",roleList);
        model.addAttribute("facultyId",facultyId);
        return "role_list";
    }

    @RequestMapping(value = "assignRole",method = POST)
    public String assignRole(Model model, Integer facultyId,Integer[] roleIds){
        roleService.assignRole(facultyId,roleIds);
        List<UserRole> roleList = roleService.queryRole();
        model.addAttribute("roleList",roleList);
        return "role_list";
    }
}