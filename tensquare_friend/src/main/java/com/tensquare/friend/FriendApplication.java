package com.tensquare.friend;

import config.JwtUtil;
import entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,1);
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
