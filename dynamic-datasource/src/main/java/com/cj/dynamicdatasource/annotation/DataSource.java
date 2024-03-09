package com.cj.dynamicdatasource.annotation;

import com.cj.dynamicdatasource.datasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface DataSource {

    String value() default DataSourceType.DEFAULT_DS_NAME;
}
