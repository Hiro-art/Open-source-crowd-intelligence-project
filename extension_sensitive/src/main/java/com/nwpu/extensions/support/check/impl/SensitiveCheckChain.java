package com.nwpu.extensions.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.constant.enums.ValidModeEnum;
import com.nwpu.extensions.support.check.ISensitiveCheck;
import com.nwpu.extensions.support.check.SensitiveCheckResult;

import java.util.List;

/**
 * 敏感词检测责任链模式
 *
 * 这里可以提供一个公共的父类。
 */
@ThreadSafe
public class SensitiveCheckChain implements ISensitiveCheck {

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 初始化责任链
        List<ISensitiveCheck> sensitiveChecks = Guavas.newArrayList();
        // 默认添加敏感词校验
        sensitiveChecks.add(Instances.singleton(SensitiveCheckWord.class));
        // 数字检测
        if(context.sensitiveCheckNum()) {
            sensitiveChecks.add(Instances.singleton(SensitiveCheckNum.class));
        }
        // 邮箱检测
        if(context.sensitiveCheckEmail()) {
            sensitiveChecks.add(Instances.singleton(SensitiveCheckEmail.class));
        }
        // Url检测
        if(context.sensitiveCheckUrl()) {
            sensitiveChecks.add(Instances.singleton(SensitiveCheckUrl.class));
        }

        // 循环调用
        for(ISensitiveCheck sensitiveCheck : sensitiveChecks) {
            SensitiveCheckResult result = sensitiveCheck.sensitiveCheck(txt, beginIndex, validModeEnum, context);
            // 有一个检测出结果即返回
            if(result.index() > 0) {
                return result;
            }
        }

        // 这里直接进行正则表达式相关的调用。
        // 默认返回 0
        return SensitiveCheckResult.of(0, SensitiveCheckChain.class);
    }

}
