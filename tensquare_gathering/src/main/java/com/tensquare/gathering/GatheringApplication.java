package com.tensquare.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatheringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class);
    }
}
