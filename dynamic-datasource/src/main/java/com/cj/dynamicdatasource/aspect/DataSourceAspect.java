package com.cj.dynamicdatasource.aspect;

import com.cj.dynamicdatasource.annotation.DataSource;
import com.cj.dynamicdatasource.datasource.DynamicDatasourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(11)
public class DataSourceAspect {

    /**
     * 拦截类  或者 方法上的注解
     */
    @Pointcut("@annotation(com.cj.dynamicdatasource.annotation.DataSource) || @within(com.cj.dynamicdatasource.annotation.DataSource)")
    public void pc(){}



    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp){

        DataSource dataSource = getDataSource(pjp);
        if (dataSource != null){
            //
            DynamicDatasourceContextHolder.setDatasourceType(dataSource.value());

            try {
                return pjp.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }finally {
                DynamicDatasourceContextHolder.clear();
            }
        }

        return null;
    }



    public DataSource getDataSource(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        if (annotation != null){
            return annotation;
        }


        return ((DataSource) pjp.getSignature().getDeclaringType().getAnnotation(DataSource.class));
        // pjp.getTarget() 获取被代理的对象  https://www.cnblogs.com/muxi0407/p/11818999.html
//        return pjp.getTarget().getClass().getAnnotation(DataSource.class);
        // 返回类上面的注解
//        Class declaringType = pjp.getSignature().getDeclaringType();
//        System.out.println(declaringType);
//        return declaringType.getClass().getAnnotation(DataSource.class);

    }
}
