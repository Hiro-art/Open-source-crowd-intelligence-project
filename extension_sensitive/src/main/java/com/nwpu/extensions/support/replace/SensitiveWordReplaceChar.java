package com.nwpu.extensions.support.replace;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.nwpu.extensions.api.ISensitiveWordReplace;
import com.nwpu.extensions.api.ISensitiveWordReplaceContext;

/**
 * 指定字符的替换策略
 */
@ThreadSafe
public class SensitiveWordReplaceChar implements ISensitiveWordReplace {

    private final char replaceChar;

    public SensitiveWordReplaceChar(char replaceChar) {
        this.replaceChar = replaceChar;
    }

    @Override
    public String replace(ISensitiveWordReplaceContext context) {
        int wordLength = context.wordLength();

        return CharUtil.repeat(replaceChar, wordLength);
    }

}
