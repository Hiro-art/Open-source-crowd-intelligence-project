package com.nwpu.extensions.support.result;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.nwpu.extensions.api.IWordResult;
import com.nwpu.extensions.api.IWordResultHandler;

/**
 * 只保留单词
 */
@ThreadSafe
public class WordResultHandlerWord implements IWordResultHandler<String> {

    @Override
    public String handle(IWordResult wordResult) {
        if(wordResult == null) {
            return null;
        }
        return wordResult.word();
    }
    
}
