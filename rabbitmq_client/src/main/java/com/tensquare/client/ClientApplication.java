package com.tensquare.client;

import com.tensquare.utils.SmsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class);
    }
    @Bean
    public com.tensquare.utils.SmsUtil smsUtil(){
        return new SmsUtil();
    }
}
