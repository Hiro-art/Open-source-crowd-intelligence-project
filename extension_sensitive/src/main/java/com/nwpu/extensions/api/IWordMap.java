package com.nwpu.extensions.api;

import com.nwpu.extensions.constant.enums.ValidModeEnum;
import com.nwpu.extensions.support.check.ISensitiveCheck;

import java.util.Collection;
import java.util.List;

/**
 * 敏感词 map
 */
public interface IWordMap extends ISensitiveCheck {


    /**
     * 初始化敏感词 map
     * @param collection 集合信息
     */
    void initWordMap(Collection<String> collection);

    /**
     * 是否包含敏感词
     * @param string 字符串
     * @param context 上下文
     * @return 是否包含
     */
    boolean contains(final String string,
                     final IWordContext context);

    /**
     * 返回所有对应的敏感词
     * @param string 原始字符串
     * @param context 上下文
     */
    List<IWordResult> findAll(final String string,
                         final IWordContext context);

    /**
     * 返回第一个对应的敏感词
     * @param string 原始字符串
     * @param context 上下文
     */
    IWordResult findFirst(final String string,
                     final IWordContext context);

    /**
     * 替换所有敏感词内容
     *
     *
     * @param target 目标字符串
     * @param replace 替换策略
     * @param context 上下文
     * @return 替换后结果
     */
    String replace(final String target,
                   final ISensitiveWordReplace replace,
                   final IWordContext context);

}
