package com.nwpu.extensions.support.check;

import com.nwpu.extensions.api.IWordContext;
import com.nwpu.extensions.constant.enums.ValidModeEnum;

/**
 * 敏感信息监测接口
 * （1）敏感词
 * （2）数字（连续8位及其以上）
 * （3）邮箱
 * （4）URL
 *
 * 可以使用责任链的模式，循环调用。
 */
public interface ISensitiveCheck {

    /**
     * 检查敏感词数量
     * <p>
     * （1）如果未命中敏感词，直接返回 0
     * （2）命中敏感词，则返回敏感词的长度。
     * <p>
     *
     * @param txt           文本信息
     * @param beginIndex    开始下标
     * @param validModeEnum 验证模式
     * @param context       执行上下文
     * @return 敏感信息对应的长度
     */
    SensitiveCheckResult sensitiveCheck(final String txt,
                                        final int beginIndex,
                                        final ValidModeEnum validModeEnum,
                                        final IWordContext context);

}
