/*
 * Copyright (c) 2018 micware Ltd.
 */

package com.gedc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableRetry
@EnableScheduling
public class WifiServiceVehicleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WifiServiceVehicleApplication.class, args);
    }

}
