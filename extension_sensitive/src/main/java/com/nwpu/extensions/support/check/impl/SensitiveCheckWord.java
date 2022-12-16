package com.nwpu.extensions.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.constant.AppConst;
import com.nwpu.extensions.constant.enums.ValidModeEnum;
import com.nwpu.extensions.support.check.ISensitiveCheck;
import com.nwpu.extensions.support.check.SensitiveCheckResult;
import com.nwpu.extensions.support.format.CharFormatChain;

import java.util.Map;

/**
 * 敏感词监测实现
 */
@ThreadSafe
public class SensitiveCheckWord implements ISensitiveCheck {

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        Map nowMap = context.sensitiveWordMap();

        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        for (int i = beginIndex; i < txt.length(); i++) {
            // 获取当前的 map 信息
            nowMap = getNowMap(nowMap, context, txt, i);

            if (ObjectUtil.isNotNull(nowMap)) {
                lengthCount++;

                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                boolean isEnd = isEnd(nowMap);
                if (isEnd) {
                    // 只在匹配到结束的时候才记录长度，避免不完全匹配导致的问题。
                    // eg: 敏感词 敏感词xxx
                    // 如果是 【敏感词x】也会被匹配。
                    actualLength = lengthCount;

                    // 这里确实需要一种验证模式，主要是为了最大匹配从而达到最佳匹配的效果。
                    if (ValidModeEnum.FAIL_FAST.equals(validModeEnum)) {
                        break;
                    }
                }
            } else {
                // 直接跳出循环
                break;
            }
        }

        return SensitiveCheckResult.of(actualLength, SensitiveCheckWord.class);
    }

    /**
     * 判断是否结束
     * BUG-FIX: 避免出现敏感词库中没有的文字。
     * @param map map 信息
     * @return 是否结束
     */
    private static boolean isEnd(final Map map) {
        if(ObjectUtil.isNull(map)) {
            return false;
        }

        Object value = map.get(AppConst.IS_END);
        if(ObjectUtil.isNull(value)) {
            return false;
        }

        return (boolean)value;
    }
    /**
     * 获取当前的 Map
     * @param nowMap 原始的当前 map
     * @param context 上下文
     * @param txt 文本信息
     * @param index 下标
     * @return 实际的当前 map
     */
    private Map getNowMap(Map nowMap,
                          final IWordContext context,
                          final String txt,
                          final int index) {
        char c = txt.charAt(index);
        char mappingChar = Instances.singleton(CharFormatChain.class).format(c, context);

        // 这里做一次重复词的处理
        Map currentMap = (Map) nowMap.get(mappingChar);
        // 启用忽略重复&当前下标不是第一个
        if(context.ignoreRepeat()
            && index > 0) {
            char preChar = txt.charAt(index-1);
            char preMappingChar = Instances.singleton(CharFormatChain.class)
                    .format(preChar, context);

            // 直接赋值为上一个 map
            if(preMappingChar == mappingChar) {
                currentMap = nowMap;
            }
        }

        return currentMap;
    }

}
