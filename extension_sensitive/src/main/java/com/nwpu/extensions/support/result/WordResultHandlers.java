package com.nwpu.extensions.support.result;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.nwpu.extensions.api.IWordResult;
import com.nwpu.extensions.api.IWordResultHandler;

/**
 * 敏感词的结果处理
 */
public final class WordResultHandlers {

    private WordResultHandlers(){}

    /**
     * 不做任何处理
     */
    public static IWordResultHandler<IWordResult> raw() {
        return Instances.singleton(WordResultHandlerRaw.class);
    }

    /**
     * 只保留单词
     */
    public static IWordResultHandler<String> word() {
        return Instances.singleton(WordResultHandlerWord.class);
    }

}
