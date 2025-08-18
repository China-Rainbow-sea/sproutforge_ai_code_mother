package com.rainbowsea.sproutforgeaicodebackend.ai;

import com.rainbowsea.sproutforgeaicodebackend.ai.model.HtmlCodeResult;
import com.rainbowsea.sproutforgeaicodebackend.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AiCodeGeneratorServiceTest {


    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;


    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做个程序员的博客，不超过20行");

        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {

        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode("做个程序员的登录页面，不超过20行");

        Assertions.assertNotNull(result);
    }
}