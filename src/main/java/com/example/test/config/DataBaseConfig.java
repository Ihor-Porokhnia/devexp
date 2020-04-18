package com.example.test.config;

import com.example.test.util.VaultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:bd.properties")
@RefreshScope
public class DataBaseConfig {

    @Value("${postgres.driver}")
    private String driverClassName;
    @Value("${postgres.url}")
    private String dataBaseUrl;
    private VaultUtil util;

    @Autowired
    public void setVaultUtil(VaultUtil util) {
        this.util = util;
        this.util.initParamForDataBase();
    }

    @Bean
    @RefreshScope
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataBaseUrl);
        dataSource.setUsername(util.getUsername());
        dataSource.setPassword(util.getPassword());
        return dataSource;
    }

}




