package com.rainbowsea.sproutforgeaicodebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SproutforgeAiCodeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SproutforgeAiCodeBackendApplication.class, args);
    }

}
