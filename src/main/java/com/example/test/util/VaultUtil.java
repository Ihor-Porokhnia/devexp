package com.example.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@PropertySource("classpath:bd.properties")
@Component
@Scope("prototype")
@Data
@Slf4j
public class VaultUtil {

    @Autowired
    private ObjectMapper mapper;
    private String username;
    private String password;
    private String jsonProp;

    @Value("${vault.token}")
    private String vaultToken;

    @Value("${vault.url}")
    private String vaultUrl;

    @Value("${vault.x}")
    private String headName;


    private String getJsonFromVault() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(vaultUrl);
        get.addHeader(headName, vaultToken);
        try {
            HttpResponse response = httpClient.execute(get);
            log.info("IN getJsonFromVault() - got HttpResponse: {}", response);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.warn("IN getJsonFromVault() - HttpResponse didn't get");
        return null;
    }

    public void initParamForDataBase() {
        jsonProp = getJsonFromVault();
        try {
            this.username = mapper.readTree(jsonProp).get("data").get("username").asText();
            this.password = mapper.readTree(jsonProp).get("data").get("password").asText();
            log.info("username and password successfully got");
        } catch (JsonProcessingException e) {
            log.info("username and password didn't got: {} ", e.getMessage());
        }
    }


}
