package com.nwpu.extensions.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.api.ICharFormat;
import com.nwpu.extensions.utils.NumUtils;

/**
 * 忽略数字的样式
 */
@ThreadSafe
public class IgnoreNumStyleCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return NumUtils.getMappingChar(original);
    }

}
