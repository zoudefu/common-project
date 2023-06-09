package com.common.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLogUtils {

    public static void log(Throwable e,Class c){
        Logger logger = LoggerFactory.getLogger(c);
        StackTraceElement s= e.getStackTrace()[0];
        logger.error("报错的文件是："+s.getFileName()+
                "报错方法是："+s.getMethodName()+
                "报错的行是："+ s.getLineNumber()+
                "报错的信息是："+ e.getMessage());
    }

}
