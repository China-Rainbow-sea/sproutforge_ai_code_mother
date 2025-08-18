package com.rainbowsea.sproutforgeaicodebackend.core.saver;

import cn.hutool.core.util.StrUtil;
import com.rainbowsea.sproutforgeaicodebackend.ai.model.HtmlCodeResult;
import com.rainbowsea.sproutforgeaicodebackend.exception.BusinessException;
import com.rainbowsea.sproutforgeaicodebackend.exception.ErrorCode;
import com.rainbowsea.sproutforgeaicodebackend.model.enums.CodeGenTypeEnum;


/**
 * HTML代码文件保存器
 *
 * @author rainbowsea
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML 代码不能为空");
        }
    }
}
