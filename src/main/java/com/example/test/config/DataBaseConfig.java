package com.example.test.config;

import com.example.test.util.VaultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Timer;
import java.util.TimerTask;

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:bd.properties")
@Slf4j
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
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataBaseUrl);
        dataSource.setUsername(util.getUsername());
        dataSource.setPassword(util.getPassword());
        updateDataSource();
        return dataSource;
    }

    private void updateDataSource() {
        TimerTask setLogoPassToDataSource = new TimerTask() {

            public void run() {
                util.initParamForDataBase();
                dataSource().setUsername(util.getUsername());
                dataSource().setPassword(util.getPassword());
                log.info("username and pass successfully update");
            }
        };

        Timer timer = new Timer();
        long delay = 1000L * 60L * 59L;
        long period = 1000L * 60L * 60L;
        timer.scheduleAtFixedRate(setLogoPassToDataSource, delay, period);
    }

}




