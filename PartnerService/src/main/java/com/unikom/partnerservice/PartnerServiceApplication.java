package com.unikom.partnerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
public class PartnerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartnerServiceApplication.class, args);
    }

}
