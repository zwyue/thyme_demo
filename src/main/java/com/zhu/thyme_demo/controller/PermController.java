package com.zhu.thyme_demo.controller;

import com.zhu.thyme_demo.config.base.BaseController;
import com.zhu.thyme_demo.entity.UserPerm;
import com.zhu.thyme_demo.service.PermService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Auther: Joanne
 * @Date: 2018/11/19 14:00
 * @Description:
 */
@Controller
public class PermController extends BaseController {

    @Autowired
    private PermService permService ;

    @RequestMapping(value = "addPerm" , method = RequestMethod.GET)
    public String addPerm(){
        return "add_perm";
    }

    @RequestMapping(value = "addPerm" , method = RequestMethod.POST)
    @RequiresPermissions("perm:addPerm")
    public String addPerm(UserPerm userPerm,Model model){
        permService.addPerm(userPerm);
        model.addAttribute("user",getLoginFaculty());
        return "admin";
    }

    @RequestMapping(value = "permList" , method = RequestMethod.GET)
    public String permList(Model model,Integer roleId){
        List<UserPerm> perms = permService.queryPerm();
        model.addAttribute("permList",perms);
        model.addAttribute("roleId",roleId);
        return "perm_list" ;
    }

    @RequestMapping(value = "assignPerm",method = RequestMethod.POST)
    public String assignPerm(Integer roleId,Integer[] permIds){
        permService.assignPerm(roleId,permIds);
        return "redirect:/permList" ;
    }

    @RequestMapping(value = "deletePerm",method = RequestMethod.POST)
    public String deletePerm(List<Integer> permIds){
        permService.deletePerm(permIds);
        return "redirect:/permList" ;
    }
}
