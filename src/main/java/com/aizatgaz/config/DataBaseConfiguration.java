package com.aizatgaz.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
public class DataBaseConfiguration {

    private final Environment environment;

    @Autowired
    public DataBaseConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUrl("jdbc:mysql://mysql-server/springmvc_crud"); // for docker
        dataSource.setUsername(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.pass"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(additionalProperties());
        sessionFactory.setPackagesToScan("com.aizatgaz.domain");
        return sessionFactory;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put(org.hibernate.cfg.Environment.DRIVER, environment.getProperty("hibernate.driverClassName"));
        properties.put(org.hibernate.cfg.Environment.DIALECT, environment.getProperty("hibernate.dialect"));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factory);
        return manager;
    }
}
