package com.nwpu.extensions.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.nwpu.extensions.api.ICharFormat;
import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.utils.CharUtils;

/**
 * 忽略英文的各种格式
 */
@ThreadSafe
public class IgnoreEnglishStyleFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return CharUtils.getMappingChar(original);
    }

}
