package com.nwpu.extensions.utils;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.nwpu.extensions.api.ICharFormat;
import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.support.format.CharFormatChain;

/**
 * 内部格式化工具类
 */
public final class InnerFormatUtils {

    private InnerFormatUtils(){}

    /**
     * 格式化
     * @param original 原始
     * @param context 上下文
     * @return 结果
     */
    public static String format(String original,  IWordContext context) {
        if(StringUtil.isEmpty(original)) {
            return original;
        }

        StringBuilder stringBuilder = new StringBuilder();
        ICharFormat charFormat = Instances.singleton(CharFormatChain.class);
        char[] chars = original.toCharArray();
        for(char c : chars) {
            char cf = charFormat.format(c, context);
            stringBuilder.append(cf);
        }

        return stringBuilder.toString();
    }

}
