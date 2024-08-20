package com.hr.microservices.config;

import java.util.HashMap;
import java.util.Map;

public class CommonDatasourceConfig {
    protected Map<String, String> jpaProperties(){
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        return jpaProperties;
    }
}
