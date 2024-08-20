package com.hr.microservices.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.hr.microservices.repository.slave",
        entityManagerFactoryRef = "slaveEntityManagerFactory",
        transactionManagerRef = "slaveTransactionManager")
public class SlaveDatasourceConfig extends CommonDatasourceConfig{

    @Bean("slaveDatasourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSourceProperties slaveDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "slaveDatasource")
    public DataSource slaveDatasource(@Qualifier("slaveDatasourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "slaveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(final EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                             @Qualifier("slaveDatasource") final DataSource datasource) {
        return entityManagerFactoryBuilder
                .dataSource(datasource)
                .packages("com.hr.microservices.entity")
                .persistenceUnit("slave")
                .properties(jpaProperties())
                .build();
    }

    @Bean(name = "slaveTransactionManager")
    public PlatformTransactionManager slaveTransactionManager(@Qualifier("slaveEntityManagerFactory")
                                                                   final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
