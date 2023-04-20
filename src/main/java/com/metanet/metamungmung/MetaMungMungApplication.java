package com.metanet.metamungmung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MetaMungMungApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetaMungMungApplication.class, args);
    }

}
