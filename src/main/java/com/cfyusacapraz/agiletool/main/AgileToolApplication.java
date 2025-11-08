package com.cfyusacapraz.agiletool.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cfyusacapraz.agiletool")
public class AgileToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgileToolApplication.class, args);
    }

}
