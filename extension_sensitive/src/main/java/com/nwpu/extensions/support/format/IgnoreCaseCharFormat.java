package com.nwpu.extensions.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.nwpu.extensions.api.ICharFormat;
import com.nwpu.extensions.api.IWordContext;

/**
 * 忽略大小写
 */
@ThreadSafe
public class IgnoreCaseCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return Character.toLowerCase(original);
    }

}
