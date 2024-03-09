package com.cj.dynamicdatasource.datasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 每次执行的时候，， 会拿到这个数据库的名字，，，
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(LoadDataSource loadDataSource) {

        // 1. 设置所有的数据源
        Map<String, DataSource> allDs = loadDataSource.loadDataSource();
        super.setTargetDataSources(new HashMap<>(allDs));

        // 2. 设置默认的数据源，，， 不是每一个方法上都有@DataSource
        super.setDefaultTargetDataSource(allDs.get(DataSourceType.DEFAULT_DS_NAME));

        //
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceContextHolder.getDatasourceType();
    }
}
