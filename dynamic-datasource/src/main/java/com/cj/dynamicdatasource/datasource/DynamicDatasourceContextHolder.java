package com.cj.dynamicdatasource.datasource;

/**
 * 將 數據庫名字 存到 threadlocal中
 */
public class DynamicDatasourceContextHolder {


    private static ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();


    public static void setDatasourceType(String dsType){
        CONTEXT_HOLDER.set(dsType);
    }


    public static String getDatasourceType(){
        return CONTEXT_HOLDER.get();
    }


    public static    void clear(){
        CONTEXT_HOLDER.remove();
    }
}
