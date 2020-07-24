package com.manage.service.aop;


import com.github.pagehelper.PageHelper;
import com.manage.service.annotation.pageHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.aoppageCommonAspect
 * @Description: pageCommonAspect
 * @Auther: ydm
 * @Date: 2020/7/15
 */
@Aspect
@Component
public class pageCommonAspect {
    private static Logger logger = LoggerFactory.getLogger(pageCommonAspect.class);

    @Pointcut("@annotation(com.manage.service.annotation.pageHelper)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object authorityHandler(ProceedingJoinPoint point) throws Throwable {
        logger.info("执行了Aspect方法");
        Object[] args = point.getArgs();
        int pageNum = 1;
        int pageSize = 15;
        try {
            for(int i=0;i<args.length;i++){
                Class<?> aClass = args[i].getClass();
                //得到所有属性
                Field[] fields = aClass.getDeclaredFields();
                for (int j=0;j<fields.length;j++){//遍历
                    //得到属性
                    Field field = fields[j];
                    //打开私有访问
                    field.setAccessible(true);
                    //获取属性
                    String name = field.getName();
                    if("pageNum".equals(name)){
                        //获取属性值
                        Object Num = field.get(args[i]);
                        pageNum = (int)Num;
                    }
                    if("pageSize".equals(name)){
                        //获取属性值
                        Object Size = field.get(args[i]);
                        pageSize = (int)Size;
                    }
                }
            }
            PageHelper.startPage(pageNum,pageSize);
            return point.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            return point.proceed();
        }
    }
}
