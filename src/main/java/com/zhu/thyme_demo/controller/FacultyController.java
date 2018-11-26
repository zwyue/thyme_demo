package com.zhu.thyme_demo.controller;

import com.zhu.thyme_demo.config.base.BaseController;
import com.zhu.thyme_demo.config.shiro.ShiroUtils;
import com.zhu.thyme_demo.entity.Faculty;
import com.zhu.thyme_demo.service.FacultyService;
import com.zhu.thyme_demo.util.FileUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * @Auther: Joanne
 * @Date: 2018/11/14 09:34
 * @Description:
 */
@Controller
public class FacultyController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FacultyController.class);

    @Autowired
    private FacultyService facultyService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        logger.info("当前用户是否被记住....{}..", ShiroUtils.getSubject().isRemembered());
        logger.info("当前用户是否被验证....{}..",ShiroUtils.getSubject().isAuthenticated());
        return "index";
    }

    @RequestMapping(value = {"downloadWord"}, method = RequestMethod.GET)
    public String downLoadWord(HttpServletResponse response) throws Exception {
        String filePath = FileUtil.downLoadWord(response);
        return null;
    }

    /**
     * 如果已经登录了，直接转跳index
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(Model model) {
        Subject subject = ShiroUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:index";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 登录成功会直接按配置转跳
     * 登录失败的时候会进入到这个函数里，可以提取出登录报错
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, Model model) {
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "登录失败";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "用户名不正确，请重新输入";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "密码错误，请重新输入";
            } else {
                msg = "发生未知错误，请联系管理员。";
            }
        }
        model.addAttribute("msg", msg);
        return "login";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String user(Model model) {
        model.addAttribute("user", getLoginFaculty());
        return "user";
    }

    @RequestMapping(value = "userList",method = RequestMethod.GET)
    public String userList(Model model) {
        List<Faculty> faculties =  facultyService.queryFaculty();
        model.addAttribute("userList",faculties);
        return "user_list";
    }

    @RequiresPermissions("user:get")
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model) {
        Faculty user = (Faculty) ShiroUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "admin";
    }

    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    public String noAuth(Model model) {
        return "403";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String addUser() {
        return "add_user";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String addUser(Faculty faculty,Model model) {
        //唯一性检验
        int exist = facultyService.countFacultyByRealName(faculty.getRealName());

        if(exist>0){
            model.addAttribute("用户已存在");
        }else {
            //学号生成
            String lastFacultyNo = facultyService.lastFacultyNo();
            int no = Integer.parseInt(lastFacultyNo.substring(8));
            String lastNo = String.valueOf(no);
            no++;
            if(no%10==no){
                lastNo = "0"+ no ;
            }
            String newNo = LocalDate.now().getYear() + (LocalDate.now().getMonthValue() + (LocalDate.now().getDayOfMonth() + lastNo));
            faculty.setFacultyNo(newNo);
            facultyService.addFaculty(faculty);
        }
        return "add_user";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register() {

        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(Faculty faculty) {
        String facultyNo = LocalDate.now().getYear()+"01"+"02"+"01";
        faculty.setFacultyNo(facultyNo);
        faculty.setSalt(facultyNo+faculty.getRealName());
        SimpleHash hash=new SimpleHash("MD5", new SimpleByteSource(faculty.getPassword()),new SimpleByteSource(faculty.getSalt()),2);
        faculty.setPassword(hash.toHex());
        facultyService.addFaculty(faculty);
        return "redirect:index";
    }
}