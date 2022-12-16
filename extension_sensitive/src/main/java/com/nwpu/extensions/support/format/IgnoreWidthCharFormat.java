package com.nwpu.extensions.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.api.ICharFormat;

/**
 * 格式化责任链
 */
@ThreadSafe
public class IgnoreWidthCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return CharUtil.toHalfWidth(original);
    }

}
