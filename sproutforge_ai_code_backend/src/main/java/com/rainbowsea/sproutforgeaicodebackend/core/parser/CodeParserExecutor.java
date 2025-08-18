package com.rainbowsea.sproutforgeaicodebackend.core.parser;


import com.rainbowsea.sproutforgeaicodebackend.exception.BusinessException;
import com.rainbowsea.sproutforgeaicodebackend.exception.ErrorCode;
import com.rainbowsea.sproutforgeaicodebackend.model.enums.CodeGenTypeEnum;

/**
 * 代码解析执行器
 * 根据代码生成类型执行相应的解析逻辑
 */
public class CodeParserExecutor {

    // HTML 单文件代码解析器
    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();

    // 多文件代码解析器（HTML + CSS + JS）
    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    /**
     * 执行代码解析
     *
     * @param codeContent
     * @param codeGenTypeEnum
     * @return
     */
    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenTypeEnum) {
        return switch (codeGenTypeEnum) {
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileCodeParser.parseCode(codeContent);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型");
        };
    }
}
