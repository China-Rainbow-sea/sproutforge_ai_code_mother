package com.rainbowsea.sproutforgeaicodebackend.service;

import jakarta.servlet.http.HttpServletResponse;


/**
 * 用户下载 AI 生成的代码到本地的服务
 */
public interface ProjectDownloadService {

    /**
     * 下载项目为压缩包
     *
     * @param projectPath
     * @param downloadFileName
     * @param response
     */
    void downloadProjectAsZip(String projectPath, String downloadFileName, HttpServletResponse response);
}
