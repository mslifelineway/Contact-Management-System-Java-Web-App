package com.oyedost.contactapp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author janeman
 */
@Configuration
@ComponentScan(basePackages = {"com.oyedost.contactapp.dao", "com.oyedost.contactapp.services"})
public class SpringRootConfig {
    // TODO: services, DAO, DataSource, Email, etc...

    @Bean
    public BasicDataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/contact_app");
        ds.setUsername("root");
        ds.setPassword("");
        ds.setMaxTotal(2);
        ds.setInitialSize(1);
        ds.setTestOnBorrow(true);
        ds.setValidationQuery("SELECT 1");
        ds.setDefaultAutoCommit(true);

        return ds;
    }

}
