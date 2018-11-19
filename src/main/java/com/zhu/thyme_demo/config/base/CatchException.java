package com.zhu.thyme_demo.config.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Joanne
 * @Date: 2018/11/16 14:58
 * @Description:
 */
@ControllerAdvice
public class CatchException {

    private static final Logger logger = LoggerFactory.getLogger(CatchException.class);

    /**
     * 全局异常处理，反正异常返回统一格式的map
     * @param ex
     * @return
     */
//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public Map<String,Object> exceptionHandler(Exception ex){
//
//        logger.info("..............捕获异常................");
//        Map<String,Object> map  = new HashMap<String,Object>();
//        map.put("code",1001);
//        map.put("msg",ex.getMessage());
//        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
//        logger.info("code:{},msg:{}",map.get("code"),map.get("msg"));
//
//        return map;
//    }

    @ExceptionHandler(value = Exception.class)
    public String handlerException(Exception ex, Model model){

        logger.info("..............捕获异常................");
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("code",1001);
        map.put("msg",ex.getMessage());
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        logger.info("code:{},msg:{}",map.get("code"),map.get("msg"));
        model.addAttribute("msg",ex.getMessage());
        return "403";
    }
}
