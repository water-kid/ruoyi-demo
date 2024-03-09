package com.cj.dynamicdatasource.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 读取配置文件
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProperties {

    private String type;
    private String driverClassName;


    private Map<String, Map<String,String>> ds;

    private Integer initialSize;


    private Integer minIdle;


    private Integer maxActive;


    /**
     *  除了 url username password，，， 其他属性都是共用的
     * @param dataSource  只有url，password，username的datasource
     * @return
     */
    public DataSource dataSource(DruidDataSource dataSource){
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMinIdle(this.minIdle);
        dataSource.setMaxActive(this.maxActive);

        return dataSource;
    }




}
