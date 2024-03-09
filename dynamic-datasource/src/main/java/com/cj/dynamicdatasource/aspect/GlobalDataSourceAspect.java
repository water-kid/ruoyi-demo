package com.cj.dynamicdatasource.aspect;

import com.cj.dynamicdatasource.datasource.DynamicDatasourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * 另一个切面，，， 前端修改 datasource,,存入session中
 *
 * 两个切面之间有先后关系，，   @Order 。。 越小的先执行
 */
@Aspect
@Component
@Order(10)
public class GlobalDataSourceAspect {

    @Autowired
    HttpSession httpSession;


    @Pointcut("execution(public * com.cj.dynamicdatasource.service.*.*(..))")
    public void pointcut(){}


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp){

        String dsName = (String) httpSession.getAttribute("dsName");

        DynamicDatasourceContextHolder.setDatasourceType(dsName);

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }


    }



}
