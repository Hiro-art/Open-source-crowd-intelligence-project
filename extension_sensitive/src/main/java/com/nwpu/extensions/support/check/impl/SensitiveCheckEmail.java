package com.nwpu.extensions.support.check.impl;

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
 * email 正则表达式检测实现。
 */
@ThreadSafe
public class SensitiveCheckEmail implements ISensitiveCheck {

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        StringBuilder stringBuilder = new StringBuilder();
        // 偷懒直接使用 String 拼接，然后结合正则表达式。
        // DFA 本质就可以做正则表达式，这样实现不免性能会差一些。
        for(int i = beginIndex; i < txt.length(); i++) {
            char currentChar = txt.charAt(i);
            char mappingChar = Instances.singleton(CharFormatChain.class)
                    .format(currentChar, context);

            if(CharUtil.isEmilChar(mappingChar)) {
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

        return SensitiveCheckResult.of(actualLength, SensitiveCheckEmail.class);
    }

    /**
     * 这里指定一个阈值条件
     * @param string 长度
     * @return 是否满足条件
     */
    private boolean isCondition(final String string) {
        return RegexUtil.isEmail(string);
    }

}
