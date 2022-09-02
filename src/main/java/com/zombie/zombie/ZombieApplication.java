package com.zombie.zombie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZombieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZombieApplication.class, args);
    }

}
