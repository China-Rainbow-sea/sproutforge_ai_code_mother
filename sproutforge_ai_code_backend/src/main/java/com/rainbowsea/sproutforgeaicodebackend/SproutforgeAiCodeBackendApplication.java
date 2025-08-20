package com.rainbowsea.sproutforgeaicodebackend;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.rainbowsea.sproutforgeaicodebackend.mapper")
public class SproutforgeAiCodeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SproutforgeAiCodeBackendApplication.class, args);
    }

}
