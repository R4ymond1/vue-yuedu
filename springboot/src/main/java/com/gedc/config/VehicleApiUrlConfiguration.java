package com.gedc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "subscription.api")
public class VehicleApiUrlConfiguration {

    private String vehicleStringUrl;

    public String getVehicleStringUrl() {
        return vehicleStringUrl;
    }

    public void setVehicleStringUrl(String vehicleStringUrl) {
        this.vehicleStringUrl = vehicleStringUrl;
    }

}
