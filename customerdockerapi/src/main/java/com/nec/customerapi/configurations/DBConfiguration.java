package com.nec.customerapi.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j

public class DBConfiguration {

    @Value("${db_url}")
	private String url;    
    @Value("${db_username}")
	private String userName;
    @Value("${db_password}")
	private String password;
    @Value("${db_driver}")
	private String driver;
    private DataSourceBuilder dataSourceBuilder;
   
    @Bean   
    public DataSource getDataSource()
    {
    	log.info("Entering Production Env.....");
        log.info("User Name..."+userName);
    	log.info("Password..."+password);
    	dataSourceBuilder=DataSourceBuilder.create();
    	dataSourceBuilder.url(url);
    	//dataSourceBuilder.username(userName);
    	//dataSourceBuilder.password(password);
    	dataSourceBuilder.username(userName);
    	dataSourceBuilder.password(password);
    	dataSourceBuilder.driverClassName(driver);
    	return dataSourceBuilder.build();
   	
    }

	

}
