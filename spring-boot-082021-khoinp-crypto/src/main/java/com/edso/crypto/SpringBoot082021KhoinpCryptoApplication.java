package com.edso.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class SpringBoot082021KhoinpCryptoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot082021KhoinpCryptoApplication.class, args);
    }

}
