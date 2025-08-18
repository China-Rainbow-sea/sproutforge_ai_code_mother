package com.rainbowsea.sproutforgeaicodebackend.ai;


import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AiCodeGeneratorServiceFactory {

    // 整合了 Spring Boot 可以自动注入我们在 yaml 配置的大模型
    @Resource
    private ChatModel chatModel;


    // 同理： 整合了 Spring Boot 可以自动注入我们在 yaml 配置的大模型
    @Resource
    private StreamingChatModel streamingChatModel;


    /**
     * 创建 AI 代码生成器服务
     *
     * @return
     */
    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService() {
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .build();
    }


}
