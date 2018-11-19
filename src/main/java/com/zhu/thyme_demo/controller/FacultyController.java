package com.zhu.thyme_demo.controller;

import com.zhu.thyme_demo.config.base.BaseController;
import com.zhu.thyme_demo.config.shiro.ShiroUtils;
import com.zhu.thyme_demo.entity.Faculty;
import com.zhu.thyme_demo.service.FacultyService;
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
import java.io.* ;
import java.time.LocalDate;

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

    @RequestMapping(value = {"download"}, method = RequestMethod.GET)
    public String wordDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = "test.txt";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("C:\\zwy\\project\\SADProgect\\test.txt");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名

                InputStream is =  new BufferedInputStream(new FileInputStream("C:\\zwy\\project\\SADProgect\\test.txt"));

                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    byte[] buffer = new byte[fis.available()];
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @RequestMapping(value = {"downloadWord"}, method = RequestMethod.GET)
    public String downLoadWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
//            exportWord();
            // path是指欲下载的文件的路径。
            File file = new File("C:\\zwy\\project\\SADProgect\\create_table.docx");
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream("C:\\zwy\\project\\SADProgect\\create_table.docx"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "success";
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