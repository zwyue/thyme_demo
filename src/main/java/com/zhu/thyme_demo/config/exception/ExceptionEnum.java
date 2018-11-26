package com.zhu.thyme_demo.config.exception;

public enum ExceptionEnum {

    UNKNOWN_EXCEPTION("10001","未知错误"),
    DATABASE_LOSE_CONNECTION("20001","数据库连接失败"),
    DATABASE_WRONG_GRAMMAR("20002","数据库语法错误")
    ;

    private String code ;
    private String message ;

    ExceptionEnum(String code,String message){
        this.code = code ;
        this.message = message ;
    }

    /**
     * @Author Joanne
     * @Description 根据code获取枚举
     * @Date 2018/11/21 12:25
     * @Param
     * @return
     **/
    public static ExceptionEnum enumOfByCode(String code){
        for(ExceptionEnum exceptionEnum:values()){
            if(code.equals(exceptionEnum.code)){
                return exceptionEnum;
            }
        }
        throw new IllegalArgumentException("no matching code for [" + code + "]");
    }

    /**
     * @Author Joanne
     * @Description 根据message获取枚举类
     * @Date 2018/11/21 12:28
     * @Param
     * @return
     **/
    private static ExceptionEnum enumOfByMsg(String msg){
        for (ExceptionEnum exceptionEnum : values()){
            if (msg.equals(exceptionEnum.message)){
                return exceptionEnum;
            }
        }
        throw new IllegalArgumentException("no matching message for [" + msg + "]");
    }
}
