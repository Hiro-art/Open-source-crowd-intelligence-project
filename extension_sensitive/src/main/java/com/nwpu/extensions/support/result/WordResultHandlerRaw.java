package com.nwpu.extensions.support.result;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.nwpu.extensions.api.IWordResult;
import com.nwpu.extensions.api.IWordResultHandler;

/**
 * 不做任何处理
 */
@ThreadSafe
public class WordResultHandlerRaw implements IWordResultHandler<IWordResult> {

    @Override
    public IWordResult handle(IWordResult wordResult) {
        return wordResult;
    }

}
