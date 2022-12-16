package com.nwpu.extensions.support.check.impl;

import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.constant.enums.ValidModeEnum;
import com.nwpu.extensions.support.check.ISensitiveCheck;
import com.nwpu.extensions.support.check.SensitiveCheckResult;
import com.nwpu.extensions.support.format.CharFormatChain;

/**
 * URL 正则表达式检测实现。
 *
 * 也可以严格的保留下来。
 *
 * （1）暂时先粗略的处理 web-site
 * （2）如果网址的最后为图片类型，则跳过。
 * （3）长度超过 70，直接结束。
 */
@ThreadSafe
public class SensitiveCheckUrl implements ISensitiveCheck {

    /**
     * 最长的网址长度
     */
    private static final int MAX_WEB_SITE_LEN = 70;

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = beginIndex; i < txt.length(); i++) {
            char currentChar = txt.charAt(i);
            char mappingChar = Instances.singleton(CharFormatChain.class)
                    .format(currentChar, context);

            if(CharUtil.isWebSiteChar(mappingChar)
                && lengthCount <= MAX_WEB_SITE_LEN) {
                lengthCount++;
                stringBuilder.append(currentChar);

                if(isCondition(stringBuilder.toString())) {
                    actualLength = lengthCount;

                    // 是否遍历全部匹配的模式
                    if(ValidModeEnum.FAIL_FAST.equals(validModeEnum)) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return SensitiveCheckResult.of(actualLength, SensitiveCheckUrl.class);
    }

    /**
     * 这里指定一个阈值条件
     * @param string 长度
     * @return 是否满足条件
     */
    private boolean isCondition(final String string) {
        return RegexUtil.isWebSite(string);
    }


}
