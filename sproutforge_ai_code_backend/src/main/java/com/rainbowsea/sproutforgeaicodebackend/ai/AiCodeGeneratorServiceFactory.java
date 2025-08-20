package com.rainbowsea.sproutforgeaicodebackend.ai;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.rainbowsea.sproutforgeaicodebackend.service.ChatHistoryService;
import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class AiCodeGeneratorServiceFactory {

    // 整合了 Spring Boot 可以自动注入我们在 yaml 配置的大模型
    @Resource
    private ChatModel chatModel;


    // 同理： 整合了 Spring Boot 可以自动注入我们在 yaml 配置的大模型
    @Resource
    private StreamingChatModel streamingChatModel;


    // 操作 Redis 用于记录，对话历史记忆
    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;

    @Resource
    private ChatHistoryService chatHistoryService;


    /**
     * AI 服务实例缓存
     * 缓存策略：
     * - 最大缓存 1000 个实例
     * - 写入后 30 分钟过期
     * - 访问后 10 分钟过期
     */
    private final Cache<Long, AiCodeGeneratorService> serviceCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMinutes(30))
            .expireAfterAccess(Duration.ofMinutes(10))
            .removalListener((key, value, cause) -> {
                log.debug("AI 服务实例被移除，appId: {}, 原因: {}", key, cause);
            })
            .build();

    /**
     * 根据 appId 获取服务（带缓存）
     */
    public AiCodeGeneratorService getAiCodeGeneratorService(long appId) {
        // serviceCache 操作 Caffeine 本地缓存，从本地缓存当中去 appId对应的 value值
        // 如果本地缓存Caffeine 没有该 appId的值，就会执行后面的 this::createAiCodeGeneratorService 方法创建一个新的 value 值返回
        return serviceCache.get(appId, this::createAiCodeGeneratorService);
    }


    /**
     * 创建新的 AI 服务实例
     *
     * @param appId
     * @return
     */
    private AiCodeGeneratorService createAiCodeGeneratorService(long appId) {
        log.info("为 appId: {} 创建新的 AI 服务实例", appId);
        // 根据 appId 构建独立的对话记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory
                .builder()
                .id(appId)
                .chatMemoryStore(redisChatMemoryStore)
                .maxMessages(20)
                .build();
        // 从数据库中加载对话历史到记忆中
        chatHistoryService.loadChatHistoryToMemory(appId, chatMemory, 20);
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();
    }


    /**
     * 创建 AI 代码生成器服务
     *
     * @return
     */
    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService() {
        return getAiCodeGeneratorService(0);
        //return AiServices.builder(AiCodeGeneratorService.class)
        //        .chatModel(chatModel)
        //        .streamingChatModel(streamingChatModel)
        //        // 根据 id 构建独立的对话记忆
        //        .build();
    }


}
