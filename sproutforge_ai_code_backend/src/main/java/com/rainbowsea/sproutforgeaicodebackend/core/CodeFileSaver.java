package com.rainbowsea.sproutforgeaicodebackend.core;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.rainbowsea.sproutforgeaicodebackend.ai.model.HtmlCodeResult;
import com.rainbowsea.sproutforgeaicodebackend.ai.model.MultiFileCodeResult;
import com.rainbowsea.sproutforgeaicodebackend.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 文件保持器
 */
@Deprecated
public class CodeFileSaver {


    // 文件保存根目录   System.getProperty("user.dir") = 项目src “同级”目录 下
    private static final String FILE_SAVE_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";


    /**
     * 保存 HTML 网页代码
     *
     * @param htmlCodeResult (单文件 html )AI返回结构化的 HTML 代码
     * @return File文件类型
     */
    public static File saveHtmlCodeResult(HtmlCodeResult htmlCodeResult) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.HTML.getValue());
        writeToFile(baseDirPath, "index.html", htmlCodeResult.getHtmlCode());
        return new File(baseDirPath);

    }


    /**
     * 保存多文件网页代码
     *
     * @param multiFileCodeResult 多文件(html,css,js) AI返回结构化的代码
     * @return File 文件类型
     */
    public static File saveMultiFileCodeResult(MultiFileCodeResult multiFileCodeResult) {
        String baseDirPath = buildUniqueDir(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeToFile(baseDirPath, "index.html", multiFileCodeResult.getHtmlCode());
        writeToFile(baseDirPath, "style.css", multiFileCodeResult.getCssCode());
        writeToFile(baseDirPath, "script.js", multiFileCodeResult.getJsCode());
        return new File(baseDirPath);
    }


    /**
     * 构建唯一目录路径：tmp/code_output/bizType_雪花ID
     *
     * @param bizType 代码生成的类型(html,css,js)
     * @return 存放文件的路径+文件名
     */
    private static String buildUniqueDir(String bizType) {

        String uniqueDirName = StrUtil.format("{}_{}", bizType, IdUtil.getSnowflakeNextIdStr());
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }


    /**
     * 写入保存单个文件
     *
     * @param dirPath  保存目录
     * @param filename 保存的文件名(包括需要指明文件后缀名)
     * @param content  保存写入到文件的内容
     */
    public static void writeToFile(String dirPath, String filename, String content) {

        //  设置文件保存的路径位置: File.separator 是 "/" 使用任何系统
        String filePath = dirPath + File.separator + filename;

        FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);

    }


}
