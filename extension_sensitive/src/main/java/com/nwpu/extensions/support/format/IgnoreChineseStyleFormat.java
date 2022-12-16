package com.nwpu.extensions.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.opencc4j.core.impl.ZhConvertBootstrap;
import com.github.houbb.opencc4j.support.segment.impl.CharSegment;
import com.nwpu.extensions.api.ICharFormat;
import com.nwpu.extensions.api.IWordContext;

/**
 * 忽略大小写
 */
@ThreadSafe
public class IgnoreChineseStyleFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        String string = String.valueOf(original);
        String simple = ZhConvertBootstrap.newInstance(new CharSegment()).toSimple(string);
        return simple.charAt(0);
    }

}
