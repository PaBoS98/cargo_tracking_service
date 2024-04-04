package com.pavlob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class CargoTrackingServiceApplication {

    @Value("${server.port}")
    private static String s;

    public static void main(String[] args) {
        SpringApplication.run(CargoTrackingServiceApplication.class, args);
        log.info("pavlo test {}", s);
    }

}
