package com.cj.dynamicdatasource.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * 将配置文件变成 datasource
 */
@EnableConfigurationProperties(value = DruidProperties.class)
@Component
public class LoadDataSource {

    @Autowired
    DruidProperties druidProperties;


    /**
     * 加载所有的数据源
     * @return
     */
    public Map<String, DataSource> loadDataSource(){
        Map<String, DataSource> map = new HashMap<>();

        druidProperties.getDs().forEach((key,dsMap)->{
            DataSource dataSource = null;
            try {
                dataSource = DruidDataSourceFactory.createDataSource(dsMap);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            map.put(key,druidProperties.dataSource(((DruidDataSource) dataSource)));

        });


        return map;
    }

}
